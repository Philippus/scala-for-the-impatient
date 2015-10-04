object Chapter6 {

  /**
   * 1. Write an object Conversions with methods inchesToCentimeters, gallonsToLiters, and
   * milesToKilometers.
   */
  object Conversions {
    // Factors taken from Wolfram Alpha
    def inchesToCentimeters(inches: Double): Double = inches * 2.54

    def gallonsToLiters(gallons: Double): Double = gallons * 3.785

    def milesToKilometers(miles: Double): Double = miles * 1.609
  }

  Conversions.inchesToCentimeters(18)
  Conversions.gallonsToLiters(10)
  Conversions.milesToKilometers(5)

  /**
   * 2. The preceding problem wasn't very object-oriented. Provide a general super-
   * class UnitConversion and define objects InchesToCentimeters, GallonsToLiters, and
   * MilesToKilometers that extend it.
   */
  abstract class UnitConversion(val factor: Double) {
    def convert(units: Double): Double = factor * units

    def apply(value: Double) = convert(value)
  }

  object InchesToCentimeters extends UnitConversion(2.54)

  object GallonsToLiters extends UnitConversion(3.785)

  object MilesToKilometers extends UnitConversion(1.609)

  InchesToCentimeters.convert(18)
  GallonsToLiters.convert(10)
  MilesToKilometers.convert(5)
  MilesToKilometers(5)

  /**
   * 3. Define an Origin object that extends java.awt.Point. Why is this not actually a
   * good idea? (Have a close look at the methods of the Point class.)
   */
  // Point has mutators, that could change the Origin.
  object Origin extends java.awt.Point

  /**
   * 4. Define a Point class with a companion object so that you can construct Point
   * instances as Point(3, 4), without using new.
   */
  class Point(val x: Int, val y: Int)

  object Point {
    def apply(x: Int, y: Int) = new Point(x, y)
  }

  val p = Point(3, 4)
  println("Point is at (" + p.x + "," + p.y + ")")

  /**
   * 5. Write a Scala application, using the App trait, that prints the command-line
   * arguments in reverse order, separated by spaces. For example, scala Reverse
   * Hello World should print World Hello.
   */
  object Reverse extends App {
    println(args.reverse.mkString(" "))
  }

  Reverse.main(Array("test1", "test2", "test3"))

  /**
   * 6. Write an enumeration describing the four playing card suits so that the toString
   * method returns ?, ?, ?, or ?.
   */
  object Suit extends Enumeration {
    type Suit = Value
    val Clubs = Value("?")
    val Diamonds = Value("?")
    val Hearts = Value("?")
    val Spades = Value("?")
  }

  println(Suit.values)

  /**
   * 7. Implement a function that checks whether a card suit value from the preceding
   * exercise is red.
   */
  def isRed(suit: Suit.Suit) = {
    suit == Suit.Diamonds || suit == Suit.Hearts
  }

  for (s <- Suit.values) yield (s, isRed(s))

  /**
   * 8. Write an enumeration describing the eight corners of the RGB color cube. As
   * IDs, use the color values (for example, 0xff0000 for Red).
   */
  object RGBCube extends Enumeration {
    val black = Value(0x000000, "Black")
    val red = Value(0xff0000, "Red")
    val green = Value(0x00ff00, "Green")
    val blue = Value(0x0000ff, "Blue")
    val yellow = Value(0xffff00, "Yellow")
    val magenta = Value(0xff00ff, "Magenta")
    val cyan = Value(0x00ffff, "Cyan")
    val white = Value(0xffffff, "White")
  }

  for (color <- RGBCube.values) println(color.id)
}
