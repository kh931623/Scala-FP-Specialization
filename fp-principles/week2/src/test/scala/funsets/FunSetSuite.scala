package funsets

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite extends munit.FunSuite:

  import FunSets.*

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets:
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)

  /**
   * This test is currently disabled (by using .ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remove the
   * .ignore annotation.
   */

  test("singleton set one contains one") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets:
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
      assert(contains(s2, 2), "Singleton")
      assert(contains(s3, 3), "Singleton")
  }

  test("union contains all elements of each set") {
    new TestSets:
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
  }

  test("intersect contains all elements that appear in both sets") {
    new TestSets:
      val u1 = union(s1, s2)
      val u2 = union(s2, s3)
      val inter = intersect(u1, u2)
      assert(!contains(inter, 1), "no 1 in intersect")
      assert(!contains(inter, 3), "no 3 in intersect")
      assert(contains(inter, 2), "2 in intersect")
  }

  test("diff contains all elements that appear in first set but not in second set") {
    new TestSets:
      val u1 = union(s1, s2)
      val u2 = union(s2, s3)
      val diffSet = diff(u1, u2)
      assert(contains(diffSet, 1), "have 1 in diff")
      assert(!contains(diffSet, 2), "no 2 in diff")
      assert(!contains(diffSet, 3), "no 3 in diff")
  }

  test("filtered set contains all original elements that pass the test") {
    new TestSets:
      val u1 = union(s1, s2)
      val last = union(u1, s3)
      val p = s1
      val filtered = filter(last, p)
      assert(contains(filtered, 1), "have 1 in filtered")
      assert(!contains(filtered, 2), "no 2 in filtered")
      assert(!contains(filtered, 3), "no 3 in filtered")
  }

  test("forall should test if all elements in a set pass the predicate") {
    new TestSets:
      val s = (input: Int) => -100 <= input && input <= 100
      val p1 = (x: Int) => -150 <= x && x <= 150
      val p2 = (x: Int) => -50 <= x && x <= 50
      assert(contains(s, -100), "contains -100")
      assert(contains(s, 100), "contains 100")
      assert(contains(s, 50), "contains 50")
      assert(!contains(s, 150), "not contains 150")
      assert(!contains(s, -150), "not contains -150")
      assert(forall(s, p1), "all elements can pass the predicate")
      assert(!forall(s, p2), "not all elements can pass the predicate")
  }

  test("exists should test if there's at least one element in the given set can pass the predicate") {
    new TestSets:
      val s = (input: Int) => -100 <= input && input <= 100
      val p = (input: Int) => input == 1
      val p2 = (input: Int) => input > 100

      assert(exists(s, p), "at least 1 element can pass the predicate")
      assert(!exists(s, p2), "no element can pass the predicate")
  }
  
  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds
