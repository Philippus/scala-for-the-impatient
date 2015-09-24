
object Chapter1 {
  /**
  2. In the Scala REPL, compute the square root of 3, and then square that value.
      By how much does the result differ from 3? (Hint: The res variables are your friend.)
  ---
  scala> math.sqrt(3)
    res0: Double = 1.7320508075688772
  scala> res0*res0
    res1: Double = 2.9999999999999996
  scala> 3-res1
    res2: Double = 4.440892098500626E-16
    */

  /**
  3. Are the res variables val or var?
  ---
  scala> res1 = res1 + 1
    <console>:9: error: reassignment to val
       res1 = res1 + 1
            ^
    */

  /**
   * 4. Scala lets you multiply a string with a number - try out "crazy" * 3 in the REPL.
   * What does this operation do? Where can you find it in Scaladoc
   */

  /**
  scala> "crazy" * 3
  res3: String = crazycrazycrazy
    */

  // Return the current string concatenated 3 times, see StringLike.*
  val exercise4 = "crazy" * 3

  /**
   * 5. What does 10 max 2 mean? In which class is the max method defined?
   */

  // Return 10 if it is greater than 2, otherwise return 2, see RichInt.max
  val exercise5 = 10 max 2

  /**
   * 6. Using BigInt, compute 2 to the power of 1024
   */
  val exercise6 = BigInt(2) pow 1024

  /**
   * 7. What do you need to import so that you can get a random prime as
   * probablePrime(100, Random), without any qualifiers before probablePrime and Random?
   */

  import scala.util.Random
  import scala.math.BigInt.probablePrime

  val exercise7 = probablePrime(100, Random)

  /**
   * 8. One way to create random file or directory names is to produce a random
   * BigInt and convert it to base 36, yielding a string such as "qsnvbevtomcj38o06kul".
   * Poke around Sacaladoc to find a way of doing this in Scala.
   */
  val exercise8 = probablePrime(100, Random) toString 36

  /**
   * 9. How do you get the first character of a string in Scala? The last character?
   */
  val exercise9a = "Hello, world!".head
  val exercise9b = "Hello, world!".last

  /**
   * 10. What do the take, drop, takeRight, and dropRight string functions do? What
   * advantage or disadvantage do they have over using substring?
   */
  val helloWorld = "Hello, world!"

  // Take the first 6 elements
  val exercise10a = helloWorld take 6
  // Drop the first 6 elements
  val exercise10b = helloWorld drop 6
  // Take the last 6 elements
  val exercise10c = helloWorld takeRight 6
  // Drop the last 6 elements
  val exercise10d = helloWorld dropRight 6

  // Advantage: with substring we need help from the length method to get similar results
  // and if helloWorld were shorter we would get Index Out of Bound errors
  helloWorld.substring(0, 6)
  helloWorld.substring(6)
  helloWorld.substring(helloWorld.length() - 6)
  helloWorld.substring(0, helloWorld.length() - 6)

  // Disadvantage: with substring we can take the middle part of a string in one method call
  helloWorld.substring(1, 6)
  helloWorld drop 1 take 5
}