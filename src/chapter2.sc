
object Chapter2 {
  /**
   * 1. The signum of a number is 1 if the number is positive, -1 if it is negative, and
   * 0 if it is zero. Write a function that computes this value.
   */
  def signum(x: Double) =
    if (x > 0) 1
    else if (x == 0) 0
    else -1

  /**
   * 2. What is the value of an empty block expression {}? What is its type?
   */

  // value = ()
  {} ==()

  // type = Unit
  def f[T](v: T) = v match {
    case _: Int => "Int"
    case _: String => "String"
    case _: Unit => "Unit"
    case _ => "Unknown"
  }

  f({}) == "Unit"

  /**
   * 3. Come up with one situation where the assignment x = y = 1 is valid in Scala.
   * (Hint: Pick a suitable type for x.)
   */

  var x: Unit = 0
  var y: Int = 0
  x = y = 1

  /**
   * 4. Write a Scala equivalent for the Java loop
   *
   * for (int i = 10;i >= 0; i--) System.out.println(i);
   */
  for (i <- 10 to(0, -1))
    println(i)

  /**
   * 5. Write a procedure countdown(n: Int) that prints the numbers from n to 0.
   */
  def countdown(n: Int) {
    for (i <- n to(0, -1)) print(i)
  }

  countdown(3)

  /**
   * 6. Write a for loop for computing the product of the Unicode codes of all letters
   * in a string. For example, the product of the characters in "Hello" is 9415087488L.
   */
  var prod = 1L
  for (i <- "Hello")
    prod *= i

  println(prod)

  /**
   * 7. Solve the preceding exercise without writing a loop. (Hint: Look at the StringOps
   * Scaladoc.)
   */
  prod = 1L
  "Hello".foreach((c: Char) => prod *= c)
  println(prod)

  /**
   * 8. Write a function product(s: String) that computes the product, as described
   * in the preceding exercises.
   */
  def product(s: String) = {
    prod = 1L
    s.foreach((c: Char) => prod *= c)
    prod
  }

  println(product("Hello"))

  /**
   * 9. Make the function of the preceding exercise a recursive function.
   */
  def productRec(s: String): Long = {
    if (s.tail.isEmpty) s.head * 1L
    else s.head * productRec(s.tail)
  }

  println(productRec("Hello"))

  /**
   * 10. Write a function that computes x^n, where n is an integer. Use the following
   * recursive definition:
   * - x^n = y^2 if n is even and positive, where y = x^(n/2)
   * - x^n = x*x^(n-1) if n is odd and positive
   * - x^0 = 1
   * - x^n = 1 / x^-n if n is negative.
   * Don't use a return statement.
   */
  def power(x: Int, n: Int): Int = {
    if (n > 0) {
      if (n % 2 == 0) {
        power(x, n / 2) * power(x, n / 2) // (from the errata: Change x^n = y^2 to x^n = y*y)
      }
      else {
        x * power(x, n - 1)
      }
    }
    else if (n == 0) 1
    else {
      1 / power(x, n - 1)
    }
  }

  power(1, 2)
  power(3, 3)
  power(4, 5)
}