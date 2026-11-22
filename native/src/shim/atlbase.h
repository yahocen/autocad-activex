/*
 * Minimal ATL shim for JACOB compilation without ATL/MFC installed.
 * Only implements the subset of ATL used by JACOB's JNI source:
 *   CComBSTR, CComPtr, CComQIPtr, CComModule, USES_CONVERSION/A2W/W2A
 */
#ifndef JACOB_ATLBASE_SHIM
#define JACOB_ATLBASE_SHIM

#include <windows.h>
#include <oleauto.h>
#include <objbase.h>
#include <malloc.h>

// =========================================================================
// CComBSTR - minimal BSTR wrapper
// =========================================================================
class CComBSTR
{
public:
    BSTR m_str;

    CComBSTR() : m_str(NULL) {}

    CComBSTR(LPCOLESTR src) : m_str(NULL)
    {
        if (src)
            m_str = SysAllocString(src);
    }

    // construct from a length and a string (JACOB uses this form)
    CComBSTR(int nLen, LPCOLESTR src) : m_str(NULL)
    {
        if (src && nLen >= 0)
            m_str = SysAllocStringLen(src, (UINT)nLen);
    }

    ~CComBSTR()
    {
        if (m_str)
            SysFreeString(m_str);
    }

    // Copy constructor (needed for new[] arrays and value passing)
    CComBSTR(const CComBSTR &other) : m_str(NULL)
    {
        if (other.m_str)
            m_str = SysAllocString(other.m_str);
    }

    // Assignment operator
    CComBSTR &operator=(const CComBSTR &other)
    {
        if (this != &other)
        {
            if (m_str)
                SysFreeString(m_str);
            m_str = other.m_str ? SysAllocString(other.m_str) : NULL;
        }
        return *this;
    }

    // implicit conversion to BSTR
    operator BSTR() const
    {
        return m_str;
    }

    // Return a copy of the BSTR (caller owns)
    BSTR Copy() const
    {
        return m_str ? SysAllocString(m_str) : NULL;
    }

    // Detach ownership
    BSTR Detach()
    {
        BSTR tmp = m_str;
        m_str = NULL;
        return tmp;
    }

    // Address-of operator (for passing as BSTR* out params)
    BSTR *operator&()
    {
        return &m_str;
    }

    // Attach: take ownership of an existing BSTR without AddRef
    void Attach(BSTR src)
    {
        if (m_str)
            SysFreeString(m_str);
        m_str = src;
    }

    // CopyTo: copy to an out parameter (caller owns the copy)
    HRESULT CopyTo(BSTR *pbstr)
    {
        if (!pbstr)
            return E_POINTER;
        *pbstr = m_str ? SysAllocString(m_str) : NULL;
        return (*pbstr || !m_str) ? S_OK : E_OUTOFMEMORY;
    }

    // Length of the BSTR in characters
    unsigned int Length() const
    {
        return m_str ? SysStringLen(m_str) : 0;
    }
};

// =========================================================================
// CComPtr - minimal smart pointer for COM interfaces
// =========================================================================
template <class T>
class CComPtr
{
public:
    T *p;

    CComPtr() : p(NULL) {}

    CComPtr(T *lp) : p(NULL)
    {
        if (lp)
        {
            lp->AddRef();
            p = lp;
        }
    }

    CComPtr(const CComPtr<T> &lp) : p(NULL)
    {
        if (lp.p)
        {
            lp.p->AddRef();
            p = lp.p;
        }
    }

    ~CComPtr()
    {
        if (p)
            p->Release();
    }

    T *operator->()
    {
        return p;
    }

    operator T *()
    {
        return p;
    }

    T &operator*()
    {
        return *p;
    }

    // For use as out-parameter: T** (e.g., &pCP)
    T **operator&()
    {
        // release existing before serving as out-param
        if (p)
        {
            p->Release();
            p = NULL;
        }
        return &p;
    }

    bool operator!() const
    {
        return (p == NULL);
    }

    CComPtr<T> &operator=(T *lp)
    {
        if (p)
            p->Release();
        p = lp;
        if (p)
            p->AddRef();
        return *this;
    }

    CComPtr<T> &operator=(const CComPtr<T> &lp)
    {
        if (this != &lp)
        {
            if (p)
                p->Release();
            p = lp.p;
            if (p)
                p->AddRef();
        }
        return *this;
    }

    // CoCreateInstance helper
    HRESULT CoCreateInstance(REFCLSID rclsid, LPUNKNOWN pUnkOuter = NULL,
                             DWORD dwClsContext = CLSCTX_ALL)
    {
        if (p)
            p->Release();
        p = NULL;
        return ::CoCreateInstance(rclsid, pUnkOuter, dwClsContext, __uuidof(T), (void **)&p);
    }
};

// =========================================================================
// CComQIPtr - smart pointer that QueryInterfaces on construction
// =========================================================================
template <class T, const IID *piid>
class CComQIPtr
{
public:
    T *p;

    CComQIPtr() : p(NULL) {}

    CComQIPtr(IUnknown *lp) : p(NULL)
    {
        if (lp)
            lp->QueryInterface(*piid, (void **)&p);
    }

    CComQIPtr(const CComQIPtr<T, piid> &lp) : p(NULL)
    {
        if (lp.p)
        {
            lp.p->AddRef();
            p = lp.p;
        }
    }

    ~CComQIPtr()
    {
        if (p)
            p->Release();
    }

    T *operator->()
    {
        return p;
    }

    operator T *()
    {
        return p;
    }

    T **operator&()
    {
        if (p)
        {
            p->Release();
            p = NULL;
        }
        return &p;
    }

    bool operator!() const
    {
        return (p == NULL);
    }
};

// =========================================================================
// CComModule - empty stub (JACOB declares _Module but never uses it)
// =========================================================================
class CComModule
{
public:
    // intentionally empty
};

// =========================================================================
// String conversion macros: USES_CONVERSION, A2W, W2A
// =========================================================================

#define USES_CONVERSION (void)0

__forceinline LPWSTR jacob_A2W(LPCSTR lpa)
{
    if (!lpa) return NULL;
    int len = MultiByteToWideChar(CP_ACP, 0, lpa, -1, NULL, 0);
    if (len <= 0) return NULL;
    LPWSTR buf = (LPWSTR)_alloca(len * sizeof(WCHAR));
    MultiByteToWideChar(CP_ACP, 0, lpa, -1, buf, len);
    return buf;
}

#define A2W(lpa) jacob_A2W(lpa)

__forceinline LPSTR jacob_W2A(LPCWSTR lpw)
{
    if (!lpw) return NULL;
    int len = WideCharToMultiByte(CP_ACP, 0, lpw, -1, NULL, 0, NULL, NULL);
    if (len <= 0) return NULL;
    LPSTR buf = (LPSTR)_alloca(len);
    WideCharToMultiByte(CP_ACP, 0, lpw, -1, buf, len, NULL, NULL);
    return buf;
}

#define W2A(lpw) jacob_W2A(lpw)

// =========================================================================
// ATLASSERT
// =========================================================================
#include <crtdbg.h>
#define ATLASSERT(expr) _ASSERTE(expr)

// =========================================================================
// _T / _TCHAR (tchar compatibility)
// =========================================================================
#ifndef _T
#define _T(x) __T(x)
#define __T(x) L ## x
#endif

#ifndef _TCHAR
typedef wchar_t _TCHAR;
#endif

// =========================================================================
// W2T - Wide to TCHAR (TCHAR = wchar_t in Unicode builds, so it's identity)
// =========================================================================
inline LPWSTR W2T(LPCWSTR lpw) { return (LPWSTR)lpw; }

// =========================================================================
// A2BSTR - ANSI to BSTR
// =========================================================================
inline BSTR A2BSTR(LPCSTR lpa)
{
    if (!lpa) return NULL;
    int len = MultiByteToWideChar(CP_ACP, 0, lpa, -1, NULL, 0);
    if (len <= 0) return NULL;
    // SysAllocStringLen allocates len chars + null, returns BSTR
    BSTR bstr = SysAllocStringLen(NULL, (UINT)(len - 1));
    if (bstr)
        MultiByteToWideChar(CP_ACP, 0, lpa, -1, bstr, len);
    return bstr;
}

#endif // JACOB_ATLBASE_SHIM
