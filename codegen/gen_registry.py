"""
Generate EntityRegistry.java — static ObjectName → entity class map.

Scans io/github/yahocen/acad sources for @AcadEntityName annotations and emits a
compile-time registry, replacing runtime classpath scanning. Runs on every
build (Build.java codegen step); stdlib only, no AutoCAD/TLB needed.
"""
import os
import re
import sys

_HERE = os.path.dirname(os.path.abspath(__file__))
_PROJECT = os.path.dirname(_HERE)
ACAD_SRC = os.path.join(_PROJECT, 'src', 'main', 'java', 'io', 'github', 'yahocen', 'acad')
OUT = os.path.join(ACAD_SRC, 'util', 'EntityRegistry.java')

ANNOTATION = re.compile(r'@AcadEntityName\("([^"]+)"\)')
PACKAGE = re.compile(r'^package\s+([\w.]+)\s*;', re.M)
CLASS = re.compile(r'\bpublic\s+class\s+(\w+)')

entries = {}  # object_name -> (package, class_name)

for root, _dirs, files in os.walk(ACAD_SRC):
    for f in files:
        if not f.endswith('.java'):
            continue
        path = os.path.join(root, f)
        with open(path, encoding='utf-8') as fh:
            content = fh.read()
        names = ANNOTATION.findall(content)
        if not names:
            continue
        pkg = PACKAGE.search(content)
        cls = CLASS.search(content)
        if not pkg or not cls:
            print(f'[WARN] {path}: annotation found but package/class not parsed, skipped')
            continue
        if len(names) != 1:
            print(f'[ERROR] {path}: expected exactly one @AcadEntityName, got {len(names)}')
            sys.exit(1)
        name = names[0]
        if name in entries:
            print(f'[ERROR] duplicate ObjectName "{name}": {entries[name]} vs {pkg.group(1)}.{cls.group(1)}')
            sys.exit(1)
        entries[name] = (pkg.group(1), cls.group(1))

if not entries:
    print('[ERROR] no @AcadEntityName classes found')
    sys.exit(1)

# Group imports by package; java.util.Map and AcadEntity are fixed.
packages = sorted({pkg for pkg, _ in entries.values()})
lines = [
    'package io.github.yahocen.acad.util;',
    '',
    'import java.util.Map;',
    'import io.github.yahocen.acad.document.base.AcadEntity;',
]
for pkg in packages:
    classes = sorted(cls for p, cls in entries.values() if p == pkg)
    for cls in classes:
        lines.append(f'import {pkg}.{cls};')
lines += [
    '',
    '/**',
    ' * 实体注册表：AutoCAD ObjectName → 实体类。',
    ' * <p>',
    ' * 由 bin/codegen/gen_registry.py 从 @AcadEntityName 注解生成，勿手改。',
    ' * 类加载即就绪，无运行时 classpath 扫描。',
    ' */',
    'public final class EntityRegistry {',
    '',
    '    private EntityRegistry() {}',
    '',
    '    public static final Map<String, Class<? extends AcadEntity>> ENTITIES = Map.ofEntries(',
]
items = sorted(entries.items())
for i, (name, (_pkg, cls)) in enumerate(items):
    comma = ',' if i < len(items) - 1 else ''
    lines.append(f'            Map.entry("{name}", {cls}.class){comma}')
lines += [
    '    );',
    '}',
    '',
]

with open(OUT, 'w', encoding='utf-8', newline='\n') as fh:
    fh.write('\n'.join(lines))

print(f'EntityRegistry: {len(entries)} entities -> {os.path.relpath(OUT, _PROJECT)}')
