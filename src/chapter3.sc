import scala.collection.mutable.ArrayBuffer

object Chapter3 {
  /**
   * 1. Write a code snippet that sets a to an array of n random integers between 0
   * (inclusive) and n (exclusive).
   */

  import util.Random

  def randomArray(n: Int) = {
    val a = new Array[Int](n)
    for (i <- a.indices) a(i) = Random.nextInt(n)
    a
  }

  val a = randomArray(5)

  /**
   * 2. Write a loop that swaps adjacent elements of an array of integers. For example,
   * Array(1, 2, 3, 4, 5) becomes Array(2, 1, 4, 3, 5).
   */
  def swapArray(a: Array[Int]): Array[Int] = {
    for (i <- a.indices) {
      if (i % 2 == 1) {
        val b = a(i)
        a(i) = a(i - 1)
        a(i - 1) = b
      }
    }
    a
  }

  swapArray(a)

  /**
   * 3. Repeat the preceding assignment, but produce a new array with the swapped
   * values. Use for/yield.
   */
  def swapArrayWithYield(a: Array[Int]): Array[Int] = {
    for (i <- a.indices) yield
    if (i % 2 == 1) a(i - 1)
    else if (i == a.length - 1) a(i)
    else a(i + 1)
    a
  }

  swapArrayWithYield(a)

  /**
   * 4. Given an array of integers, produce a new array that contains all positive
   * values of the original array, in their original order, followed by all values that
   * are zero or negative, in their original order.
   */
  def posnegArray(a: Array[Int]): Array[Int] = {
    val positives = for (elem <- a if elem > 0) yield elem
    val negatives = for (elem <- a if elem <= 0) yield elem
    positives ++ negatives
  }

  posnegArray(Array(-3, 3, 0, -5, 2, 5))

  /**
   * 5. How do you compute the average of an Array[Double]?
   */
  def averageArray(a: Array[Double]): Double = {
    a.sum / a.length
  }

  averageArray(Array(5.0, 2.0, 3.0))

  /**
   * 6. How do you rearrange the elements of an Array[Int] so that they appear in
   * reversed sorted order? How do you do the same with an ArrayBuffer[Int]?
   */
  def reverseSortArray(a: Array[Int]): Array[Int] = {
    scala.util.Sorting.quickSort(a)
    a.reverse
  }

  reverseSortArray(Array(3, 2, 18, 5, 0))

  def reverseSortArrayBuffer(a: ArrayBuffer[Int]): ArrayBuffer[Int] = {
    a.sortWith(_ > _)
  }

  reverseSortArrayBuffer(ArrayBuffer(3, 2, 18, 5, 0))

  /**
   * 7. Write a code snippet that produces all values from an array with duplicates
   * removed. (Hint: Look at Scaladoc.)
   */
  def removeDuplicates(a: Array[Int]): Array[Int] = {
    a.distinct
  }

  removeDuplicates(Array(5, 1, 2, 2, 3, 5))

  /**
   * 8. Rewrite the example at the end of Section 3.4, "Transforming Arrays," on
   * page 32. Collect indexes of the negative elements, reverse the sequence, drop
   * the last index, and call a.remove(i) for each index. Compare the efficiency of
   * this approach with the two approaches in Section 3.4.
   */

  def removeAllButFirst(a: ArrayBuffer[Int]): ArrayBuffer[Int] = {
    def findNegIndexes(a: ArrayBuffer[Int]) = for (i <- a.indices if a(i) < 0) yield i
    val negIndexes: IndexedSeq[Int] = findNegIndexes(a).reverse.dropRight(1)
    for (negIndex <- negIndexes) a.remove(negIndex)
    a
  }

  removeAllButFirst(ArrayBuffer(-1, 3, -4, -5, 6, -2))
  /**
   * 9. Make a collection of all time zones returned by java.util.TimeZone.getAvailableIDs
   * that are in America. Strip off the "America/" prefix and sort the result.
   */
  val americanTimeZones = java.util.TimeZone.getAvailableIDs().filter(_ startsWith ("America/")).map(_.stripPrefix("America/")).sorted

  /**
   * 10. Import java.awt.datatransfer._ and make an object of type SystemFlavorMap with
   * the call
   * val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
   * Then call the getNativesForFlavor method with parameter DataFlavor.imageFlavor
   * and get the return value as a Scala buffer. (Why this obscure class? It's hard
   * to find uses of java.util.List in the standard Java library.)
   */

  import java.awt.datatransfer._

  val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]

  import scala.collection.JavaConversions.asScalaBuffer
  import scala.collection.mutable.Buffer

  val natives: Buffer[String] = flavors.getNativesForFlavor(DataFlavor.imageFlavor)
}
