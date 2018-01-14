# BWDM - Boundary Value/Vienna Development Method -
#### Test Cases Auto-Generation from a VDM Specification



## Execution Environment, using Libraries,
* OS : Any OS (Please prepare same enviroment.)
* [Java9](https://www.oracle.com/java/java9.html) : Execution Environment
* [VDMJ Ver.4](https://github.com/nickbattle/vdmj.git) : Lexer and Parser
* [JUnit5](https://github.com/junit-team/junit5) : Unit Testing
* [z3](https://github.com/Z3Prover/z3) : Theorem Prover

## History
* 20161110  create first repository.
* 20161111  implement FX.java. 
* 20170105  add 'test' package. rename some directories.
* 20171025  create new repository, change internal construction of BWDM.
* 20180115
  * Implementation for my master paper was almost done :)
  * BVA and Symbolic Exe. are available for auto-gen of testcase.
  * Command-line options are also available (below table).


| Option        | Content          |
| --------------- |:---------------|
| -a | Info. of BVA                  |
| -i | Info. of Symbolic Exe.        |
| -b | Hide testcase made by Symbolic Exe. |
| -s | Hide testcase made by BVA  |
| -f | Output testcase into a file (default:display on console)|
| -v | Version |
| -h | Help |