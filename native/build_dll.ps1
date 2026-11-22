# build_dll.ps1 — compile jacobcom.dll with MSVC + JDK21
$ErrorActionPreference = 'Stop'

$MSVC = 'C:\Program Files\Microsoft Visual Studio\18\Community\VC\Tools\MSVC\14.50.35717'
$WINSDK = 'C:\Program Files (x86)\Windows Kits\10'
$JDK = 'C:\Application\VMR\versions\jdk_versions\jdk-21.0.7+6-openj9'
$PROJECT = $PSScriptRoot | Split-Path
$SRC = "$PROJECT\native\src"
$OUT = "$PROJECT\src\main\resources\native"
if (-not (Test-Path $OUT)) { New-Item -ItemType Directory -Path $OUT -Force | Out-Null }

# --- set up environment via vcvars64 ---
$vcvars = 'C:\Program Files\Microsoft Visual Studio\18\Community\VC\Auxiliary\Build\vcvars64.bat'
$envOutput = cmd /c "`"$vcvars`" >nul 2>&1 && set" 2>&1
foreach ($line in $envOutput) {
    if ($line -match '^([^=]+)=(.*)') {
        $key = $matches[1]; $val = $matches[2]
        Set-Item -Path "Env:$key" -Value $val
    }
}
Write-Host "=== cl.exe: $((Get-Command cl -ErrorAction SilentlyContinue).Source)"

# --- include paths ---
$incShim   = "$SRC\shim"
$incJni    = "$JDK\include"
$incJniWin = "$JDK\include\win32"
$incSDKum  = "$WINSDK\Include\10.0.26100.0\um"
$incSDKsh  = "$WINSDK\Include\10.0.26100.0\shared"
$incSDKuc  = "$WINSDK\Include\10.0.26100.0\ucrt"
$incMSVC   = "$MSVC\include"

$includes = "/I`"$incShim`" /I`"$SRC`" /I`"$incJni`" /I`"$incJniWin`" /I`"$incSDKum`" /I`"$incSDKsh`" /I`"$incSDKuc`" /I`"$incMSVC`""

# --- lib paths ---
$libJDK   = "$JDK\lib"
$libSDKum = "$WINSDK\Lib\10.0.26100.0\um\x64"
$libSDKuc = "$WINSDK\Lib\10.0.26100.0\ucrt\x64"
$libMSVC  = "$MSVC\lib\x64"
$libpaths = "/LIBPATH:`"$libJDK`" /LIBPATH:`"$libSDKum`" /LIBPATH:`"$libSDKuc`" /LIBPATH:`"$libMSVC`""

$cflags = '/c /nologo /MT /EHsc /O2 /W3 /utf-8 /D UNICODE /D _UNICODE /D _WIN32_WINNT=0x0A00 /D _CRT_SECURE_NO_DEPRECATE /D _CRT_NONSTDC_NO_DEPRECATE /D _STATIC_CPPLIB'
$libs = 'jvm.lib oleaut32.lib ole32.lib advapi32.lib user32.lib'

$srcFiles = @('ComThread','Dispatch','DispatchEvents','DispatchProxy','EnumVariant','EventProxy','SafeArray','STA','util','Variant')

Write-Host "=== Compiling ==="
$objs = @()
foreach ($f in $srcFiles) {
    $srcFile = "$SRC\$f.cpp"
    $objFile = "$OUT\$f.obj"
    $cmd = "cl $cflags $includes `"$srcFile`" /Fo`"$objFile`""
    Write-Host "  $f.cpp..."
    $result = cmd /c "$cmd 2>&1"
    if ($LASTEXITCODE -ne 0) {
        Write-Host $result
        Write-Error "Compile failed: $f.cpp"
        exit 1
    }
    $objs += $objFile
}

Write-Host "=== Linking ==="
$objArgs = ($objs | ForEach-Object { "`"$_`"" }) -join ' '
$cmd = "link /nologo /DLL /OUT:`"$OUT\jacobcom.dll`" $libpaths $libs $objArgs"
Write-Host "  link..."
$result = cmd /c "$cmd 2>&1"
if ($LASTEXITCODE -ne 0) {
    Write-Host $result
    Write-Error "Link failed"
    exit 1
}
Write-Host $result

# 清理 obj 文件
Write-Host "=== Cleaning ==="
Remove-Item "$OUT\*.obj" -ErrorAction SilentlyContinue
Remove-Item "$OUT\jacobcom.lib" -ErrorAction SilentlyContinue
Remove-Item "$OUT\jacobcom.exp" -ErrorAction SilentlyContinue

$dll = "$OUT\jacobcom.dll"
if (Test-Path $dll) {
    $sz = (Get-Item $dll).Length
    Write-Host "=== Success: $dll ($sz bytes) ==="
} else {
    Write-Error "DLL not found: $dll"
    exit 1
}
