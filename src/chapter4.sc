object Chapter4 {
  /**
   * 1. Set up a map of prices for a number of gizmos that you covet. Then produce
   * a second map with the same keys and the prices at a 10 percent discount.
   */
  val gizmos = Map("Car" -> 100, "Bike" -> 20, "Scooter" -> 5)
  val discountedGizmos = for ((k, v) <- gizmos) yield (k, v * 0.9)

  /**
   * 2. Write a program that reads words from a file. use a mutable map tot count
   * how often each word appears. To read the words, simply use java.util.Scanner:
   *
   * val in = new java.util.Scanner(new java.io.File("myfile.txt"))
   * while (in.hasNext()) process in.next()
   *
   * Or look at Chapter 9 for a Scalaesque way.
   * At the end, print out all words and their counts.
   */
  val countWords = scala.collection.mutable.Map[String, Int]()
  val in = new java.util.Scanner(new java.io.File("c:/myfile.txt"))
  while (in.hasNext()) {
    val nextWord = in.next()
    if (countWords.contains(nextWord)) {
      countWords(nextWord) = countWords(nextWord) + 1
    } else {
      countWords(nextWord) = 1
    }
  }
  in.close()
  countWords.foreach(println(_))

  /**
   * 3. Repeat the preceding exercise with an immutable map.
   */
  var countWordsImmutable = Map[String, Int]()
  val inImmutable = new java.util.Scanner(new java.io.File("c:/myfile.txt"))
  while (inImmutable.hasNext()) {
    val nextWord = inImmutable.next()
    if (countWordsImmutable.contains(nextWord)) {
      countWordsImmutable = countWordsImmutable + (nextWord -> (countWordsImmutable(nextWord) + 1))
    } else {
      countWordsImmutable = countWordsImmutable + (nextWord -> 1)
    }
  }
  inImmutable.close()
  countWordsImmutable.foreach(println(_))
  /**
   * 4. Repeat the preceding exercise with a sorted map, so that the words are
   * printed in sorted order.
   */
  var countWordsSorted = scala.collection.immutable.SortedMap[String, Int]()
  val inSorted = new java.util.Scanner(new java.io.File("c:/myfile.txt"))
  while (inSorted.hasNext()) {
    val nextWord = inSorted.next()
    if (countWordsSorted.contains(nextWord)) {
      countWordsSorted = countWordsSorted + (nextWord -> (countWordsSorted(nextWord) + 1))
    } else {
      countWordsSorted = countWordsSorted + (nextWord -> 1)
    }
  }
  inSorted.close()
  countWordsSorted.foreach(println(_))

  /**
   * 5. Repeat the preceding exercise with a java.util.TreeMap that you adapt to the
   * Scala API.
   */

  import scala.collection.JavaConversions.mapAsScalaMap

  var countWordsTreeMap: scala.collection.mutable.Map[String, Int] = new java.util.TreeMap[String, Int]
  val inTreeMap = new java.util.Scanner(new java.io.File("c:/myfile.txt"))
  while (inTreeMap.hasNext()) {
    val nextWord = inTreeMap.next()
    if (countWordsTreeMap.contains(nextWord)) {
      countWordsTreeMap(nextWord) = countWordsTreeMap(nextWord) + 1
    } else {
      countWordsTreeMap(nextWord) = 1
    }
  }
  inTreeMap.close()
  countWordsTreeMap.foreach(println(_))

  /**
   * 6. Define a linked hash map that maps "Monday" to java.util.Calendar.MONDAY, and
   * similarly for the other weekdays. Demonstrate that the elements are visited
   * in insertion order.
   */

  import java.util.Calendar._

  val linkedHashMap = scala.collection.mutable.LinkedHashMap(
    "Monday" -> MONDAY,
    "Tuesday" -> TUESDAY,
    "Friday" -> FRIDAY,
    "Wednesday" -> WEDNESDAY,
    "Thursday" -> THURSDAY,
    "Saturday" -> SATURDAY,
    "Sunday" -> SUNDAY)
  println(linkedHashMap)

  /**
   * 7. Print a table of all Java properties, like this:
   *
   * java.runtime.name              | Java(TM) SE Runtime Environment
   * sun.boot.library.path          | /home/apps/jdk1.6.0_21/jre/lib/i386
   * java.vm.version                | 17.0-b16
   * java.vm.vendor                 | Sun Microsystems Inc.
   * java.vendor.url                | http://java.sun.com/
   * path.separator                 | :
   * java.vm.name                   | Java HotSpot(TM) Server VM
   *
   * You need to find the length of the longest key before you can print the table.
   */
  val properties = java.lang.System.getProperties()
  var longestLength = 0
  for (property <- properties) {
    if (property._1.toString().length() > longestLength) longestLength = property._1.toString().length()
  }
  for (property <- properties) {
    print(property._1.toString())
    for (i <- property._1.toString().length to longestLength) {
      print(" ")
    }
    print(" | ")
    println(property._2.toString())
  }

  /**
   * 8. Write a function minmax(values: Array[Int]) that returns a pair containing the
   * smallest and largest value in the array.
   */
  def minmax(values: Array[Int]): (Int, Int) = {
    (values.min, values.max)
  }

  minmax(Array(0, -10, 4, 8, -7, 33, 4))

  /**
   * 9. Write a function lteqgt(values: Array[Int], v: Int) that returns a triple containing
   * the counts of values less than v, equal to v, and greater than v.
   */
  def lteqgt(values: Array[Int], v: Int): (Int, Int, Int) = {
    val (eq, noteq) = values.partition(_ == v)
    val (lt, gt) = noteq.partition(_ < v)
    (lt.length, eq.length, gt.length)
  }

  lteqgt(Array(0, -10, 5, 8, -7, 33, 4), 5)

  /**
   * 10. What happens when you zip together two strings, such as "Hello".zip("World")?
   * Come up with a plausible use case.
   */
  "Hello".zip("World")
  // could have some use in "cryptography" (rot13)
  "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".zip("NOPQRSTUVWXYZABCDEFGHIJKLMnopqrstuvwxyzabcdefghijklm")
  // or changing the case of characters
  "ABCDEFGHIJKLMNOPQRSTUVWXYZ".zip("abcdefghijklmnopqrstuvwxyz")
  // eq. with
  ('A' to 'Z').zip('a' to 'z')
}
