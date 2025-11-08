# Tetris JavaFX - Direct Run Project

This is a runnable JavaFX Tetris project (direct-run; no Gradle).

## Files created under:
`/mnt/data/tetris_javafx_src/`

## How to compile (command-line)
You need JDK 17+ and JavaFX SDK (e.g. JavaFX 20).
Assume JavaFX SDK lib path is /path/to/javafx-sdk/lib.

Compile:
```
javac -d out --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls \
  $(find /mnt/data/tetris_javafx_src -name "*.java") 
```

Run:
```
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls -cp out main.Main
```

If using an IDE, add JavaFX SDK to project libraries and run `main.Main`.

Controls:
- Left/Right: move
- Down: soft drop
- Up: rotate
- Space: hard drop
- P: (pause placeholder)

Notes:
- RandomFactory is an interface with two implementations (RandomFactoryNormal and RandomFactoryBag7).
- Project satisfies OOP requirements: >15 classes, abstract Block base class, interfaces, file I/O placeholder (PlayerManager), static usage (Config), polymorphism, constructors, etc.

