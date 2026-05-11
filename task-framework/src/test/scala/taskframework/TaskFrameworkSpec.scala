package taskframework

import org.scalatest.funsuite.AnyFunSuite

class TaskFrameworkSpec extends AnyFunSuite {

  test("execute simple task") {

    val pool =
      new TaskThreadPool(2)

    val task =
      new SimpleTask[Int](
        () => 10 + 20
      )

    val future =
      pool.submit(task)

    val result =
      future.await()

    assert(result.completed)
    assert(result.value.contains(30))
    assert(result.error.isEmpty)

    pool.shutdown()
  }

  test("execute string task") {

    val pool =
      new TaskThreadPool(1)

    val task =
      new SimpleTask[String](
        () => "hello"
      )

    val result =
      pool.submit(task).await()

    assert(result.value.contains("hello"))

    pool.shutdown()
  }

  test("failed task returns error") {

    val pool =
      new TaskThreadPool(2)

    val task =
      new SimpleTask[Int](
        () => throw new RuntimeException("boom")
      )

    val result =
      pool.submit(task).await()

    assert(result.completed)
    assert(result.error.nonEmpty)

    pool.shutdown()
  }

  test("multiple tasks execute correctly") {

    val pool =
      new TaskThreadPool(4)

    val futures =
      (1 to 100).map { i =>

        pool.submit(
          new SimpleTask[Int](
            () => i * 2
          )
        )
      }

    val results =
      futures.map(_.await())

    assert(results.size == 100)

    val values =
      results.flatMap(_.value)

    assert(values.contains(2))
    assert(values.contains(200))

    pool.shutdown()
  }

  test("worker count matches configuration") {

    val pool =
      new TaskThreadPool(5)

    assert(pool.workerCount() == 5)

    pool.shutdown()
  }

  test("pool shutdown changes running state") {

    val pool =
      new TaskThreadPool(2)

    assert(pool.isRunning())

    pool.shutdown()

    assert(!pool.isRunning())
  }

  test("queue size eventually drains") {

    val pool =
      new TaskThreadPool(1)

    (1 to 20).foreach { i =>

      pool.submit(
        new SimpleTask[Int](
          () => {
            Thread.sleep(10)
            i
          }
        )
      )
    }

    Thread.sleep(500)

    assert(pool.queueSize() == 0)

    pool.shutdown()
  }

  test("future completion state changes") {

    val pool =
      new TaskThreadPool(1)

    val future =
      pool.submit(
        new SimpleTask[Int](
          () => {
            Thread.sleep(50)
            99
          }
        )
      )

    assert(!future.isCompleted())

    val result =
      future.await()

    assert(result.completed)
    assert(future.isCompleted())

    pool.shutdown()
  }

  test("high volume task execution") {

    val pool =
      new TaskThreadPool(8)

    val futures =
      (1 to 1000).map { i =>

        pool.submit(
          new SimpleTask[Int](
            () => i
          )
        )
      }

    val results =
      futures.map(_.await())

    assert(results.count(_.completed) == 1000)

    val values =
      results.flatMap(_.value)

    assert(values.contains(1))
    assert(values.contains(1000))

    pool.shutdown()
  }

  test("tasks execute concurrently") {

    val pool =
      new TaskThreadPool(4)

    val start =
      System.currentTimeMillis()

    val futures =
      (1 to 4).map { _ =>

        pool.submit(
          new SimpleTask[Int](
            () => {
              Thread.sleep(300)
              1
            }
          )
        )
      }

    futures.foreach(_.await())

    val elapsed =
      System.currentTimeMillis() - start

    assert(elapsed < 1000)

    pool.shutdown()
  }

  test("await with timeout returns result") {

    val pool =
      new TaskThreadPool(2)

    val future =
      pool.submit(
        new SimpleTask[String](
          () => "done"
        )
      )

    val result =
      future.await(1000)

    assert(result.value.contains("done"))

    pool.shutdown()
  }

  test("task preserves execution order in single worker") {

    val pool =
      new TaskThreadPool(1)

    val futures =
      (1 to 10).map { i =>

        pool.submit(
          new SimpleTask[Int](
            () => i
          )
        )
      }

    val results =
      futures.map(_.await().value.get)

    assert(results == (1 to 10))

    pool.shutdown()
  }

  test("large computation task") {

    val pool =
      new TaskThreadPool(4)

    val future =
      pool.submit(
        new SimpleTask[Long](
          () => {
            (1L to 100000L).sum
          }
        )
      )

    val result =
      future.await()

    assert(result.value.contains(5000050000L))

    pool.shutdown()
  }

  test("multiple failures handled independently") {

    val pool =
      new TaskThreadPool(3)

    val futures =
      (1 to 20).map { _ =>

        pool.submit(
          new SimpleTask[Int](
            () => throw new IllegalStateException("failure")
          )
        )
      }

    val results =
      futures.map(_.await())

    assert(results.forall(_.error.nonEmpty))

    pool.shutdown()
  }

  test("mixed successful and failed tasks") {

    val pool =
      new TaskThreadPool(4)

    val success =
      pool.submit(
        new SimpleTask[Int](
          () => 42
        )
      )

    val failure =
      pool.submit(
        new SimpleTask[Int](
          () => throw new RuntimeException("x")
        )
      )

    val successResult =
      success.await()

    val failureResult =
      failure.await()

    assert(successResult.value.contains(42))
    assert(failureResult.error.nonEmpty)

    pool.shutdown()
  }
}