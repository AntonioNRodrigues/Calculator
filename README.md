# Calculator

A small postfix (Reverse Polish Notation) calculator implemented with a custom array-backed stack. It is a plain Java 8 project with no external dependencies.

## Requirements

- Java 25 runtime (`java` on `PATH`). A full JDK is not required; the source-file launcher is enough.

## Run

From the repository root:

```bash
java src/RunCalculator.java data.txt result2.txt src/ArrayStack.java src/Calculator.java src/Stack.java
```

The first argument is the input file and the second is the output file. Both are optional; the defaults are `data.txt` and `result2.txt`.

Run the stack smoke test:

```bash
java src/StackRandomTest.java src/ArrayStack.java src/Stack.java
```

## Input language

Each non-blank, non-comment token is processed left to right:

| Token | Meaning |
| --- | --- |
| integer | Push onto the stack |
| `+`, `-`, `*`, `/`, `%` | Apply the operator to the top two values |
| `dup` | Duplicate the top value |
| `drop` | Remove the top value |
| `swap` | Swap the top two values |
| `@x` | Store the top value in `x` |
| `x` | Push the stored `x` value |
| `.` | Print and pop the top value |

Lines starting with `-` are comments. Blank lines are ignored. Malformed expressions, unknown tokens and division by zero now raise clear errors instead of silently corrupting the stack.

## Expected output

Running the bundled `data.txt` writes the same values as `result.txt`:

```text
6
2
8
0
2
6
100
100
-200
-200
12345
4100
100
```

## License

MIT — see `LICENSE`.
