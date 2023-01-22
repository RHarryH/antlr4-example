[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

# ANTLR4 Example

This project contains four examples of simple calculator grammar:

- `Calc.g4` contains just grammar without any actions attached
- `CalcSemantic.g4` contains grammar with semantic actions allowing to compute the result of expression
- `CalcPatters.g4` is a variant of grammar with rules labels for use with Listener and Visitor patterns
  - for Listener pattern please check `CalcListenerImpl` implementation
  - for Visitor pattern please check `CalcVisitorImpl` implementation

Each example has its corresponding test in `ANTLRExampleTest` class