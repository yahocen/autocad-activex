/*
 * jcompat.h - Compatibility wrappers for modern MSVC strictness
 *
 * Modern MSVC (14.30+) treats int and long as distinct types even though
 * both are 32-bit on Windows. Old MSVC allowed implicit int* -> long*
 * pointer conversion. These wrappers use void* to accept either type.
 *
 * This header must be included AFTER <jni.h> and <oleauto.h>.
 */
#ifndef JACOB_JCOMPAT_H
#define JACOB_JCOMPAT_H

// SafeArrayGetElement: original takes LONG*, JACOB passes jint* or long*
inline HRESULT jacob_SafeArrayGetElement(SAFEARRAY *psa, void *rgIndices, void *pv)
{
    return ::SafeArrayGetElement(psa, reinterpret_cast<LONG*>(rgIndices), pv);
}
#define SafeArrayGetElement jacob_SafeArrayGetElement

// SafeArrayPutElement: original takes LONG*, JACOB passes jint* or long*
inline HRESULT jacob_SafeArrayPutElement(SAFEARRAY *psa, void *rgIndices, void *pv)
{
    return ::SafeArrayPutElement(psa, reinterpret_cast<LONG*>(rgIndices), pv);
}
#define SafeArrayPutElement jacob_SafeArrayPutElement

// SafeArrayGetLBound: original takes LONG*, JACOB passes jint* or long*
inline HRESULT jacob_SafeArrayGetLBound(SAFEARRAY *psa, UINT nDim, void *plLbound)
{
    return ::SafeArrayGetLBound(psa, nDim, reinterpret_cast<LONG*>(plLbound));
}
#define SafeArrayGetLBound jacob_SafeArrayGetLBound

// SafeArrayGetUBound: original takes LONG*, JACOB passes jint* or long*
inline HRESULT jacob_SafeArrayGetUBound(SAFEARRAY *psa, UINT nDim, void *plUbound)
{
    return ::SafeArrayGetUBound(psa, nDim, reinterpret_cast<LONG*>(plUbound));
}
#define SafeArrayGetUBound jacob_SafeArrayGetUBound

#endif // JACOB_JCOMPAT_H
