# gatling_performance
Sample Gatling performance testing framework with SBT for dependency management

To run all simulations

```bash
$ sbt test
```

To run a single simulation (useful for executing in CI)

```bash
$ sbt testOnly <test class name with package ex. tests.SampleTest>
```

To list the tasks possible in SBT

```bash
$ sbt tasks
```
