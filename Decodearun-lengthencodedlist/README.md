# Decodearun-lengthencodedlist - P12

10 implementations of the classic "99 Scala Problems" exercise **P12**:

> Given a run-length code list generated as specified in problem P10,
> construct its uncompressed version.
>
> ```
> scala> decode(List((4, Symbol("a")), (1, Symbol("b")), (2, Symbol("c")), (2, Symbol("a")), (1, Symbol("d")), (4, Symbol("e"))))
> res0: List[Symbol] = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
> ```

Everything lives in a single sbt project, package `P12`, Scala **3.8.4**,
written with braces (no significant-indentation syntax). Every class
implements the exact same generic public API:

```scala
def decode[A](list: List[(Int, A)]): List[A]
```

`Symbol("a")` is used instead of the old `'a` symbol-literal syntax, which
Scala 3 no longer supports.

## The 10 algorithms

| Class | Approach |
|-------|----------|
| `P01` | Direct recursion with hand-written `replicate` and `append` |
| `P02` | Tail-recursive, explicit `prependN` counter + reverse-accumulator |
| `P03` | Single streaming pass, mutable `ListBuffer` and a nested counting loop |
| `P04` | Two-pass, array based: sum counts first, then fill by index |
| `P05` | Continuation-passing style (CPS) recursion |
| `P06` | Mutual recursion (`decodeFrom` / `expandCount`) |
| `P07` | Divide and conquer over the tuple list, plain concatenation of halves |
| `P08` | Recursive-doubling `replicate` (fast-exponentiation style) |
| `P09` | Hand-rolled dynamic array output (no library collections at all) |
| `P10` | Solution built on top of a self-implemented generic `foldRight` |

A `Main` object (`P12.Main`) runs the example input through all ten
classes and prints their results side by side.

## Project layout

```
Decodearun-lengthencodedlist/
├── build.sbt
├── project/
│   └── build.properties
├── src/
│   ├── main/scala/P12/
│   │   ├── P01.scala ... P10.scala
│   │   └── Main.scala
│   └── test/scala/P12/
│       └── P01Test.scala ... P10Test.scala
└── README.md
```

## Running

```bash
sbt compile
sbt test
sbt run
```

## Tests

Every class has a matching `PxxTest` (ScalaTest `AnyFunSuite` +
`Matchers`) covering:

- the exact example from the problem statement
- an empty list
- a single tuple with count one
- a single tuple with a larger count
- several tuples that all have count one
- a generic element type other than `Symbol` (`Int`, `String`)
- a count large enough to exercise multi-step expansion (10, to stress
  the recursive-doubling algorithm in particular)

## A note on verification in this environment

This sandbox has no Scala/sbt toolchain and no network access to fetch
one, so the code could not be compiled here. Every algorithm's logic was
instead re-implemented in Python and executed against the example plus a
battery of edge cases (empty list, count of one, larger counts up to ten,
several singleton tuples, mixed element types) to confirm all ten produce
byte-for-byte the same results before being translated to Scala, and every
file was manually re-checked for brace/paren/bracket balance and for the
two mistakes that came up in earlier rounds (a `@tailrec` method with a
non-tail recursive call, and Scala 3's removal of `'symbol` literals).
Please run `sbt test` on your machine to get a compiler-verified
confirmation; if anything still fails to compile, share the error and it
will be fixed right away.
