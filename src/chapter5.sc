object Chapter5 {

  /**
   * 1. Improve the Counter class in Section 5.1, "Simple Classes and Parameterless
   * Methods," on page 49 so that it doesn't turn negative at Int.MaxValue.
   */
  class Counter(v: Int) {
    // You must initialize the field
    private var value = v

    // Methods are public by default
    def increment() {
      if (value < Int.MaxValue) value += 1
    }

    def current() = value
  }

  var c = new Counter(Int.MaxValue)
  c.increment()
  c.current()

  /**
   * 2. Write a class BankAccount with methods deposit and withdraw, and a read-only
   * property balance.
   */
  // we can not use a val, because we want to change the balance with deposit and withdraw
  class BankAccount {
    private var privateBalance = 0

    def deposit(value: Int) = {
      privateBalance += value
    }

    def withdraw(value: Int) = {
      privateBalance -= value
    }

    def balance = privateBalance
  }

  /**
   * 3. Write a class Time with read-only properties hours and minutes and a method
   * before(other: Time): Boolean that checks whether this time comes before the
   * other. A Time object should be constructed as new Time(hrs, min), where hrs is in
   * military time format (between 0 and 23).
   */
  class Time(val hours: Int, val minutes: Int) {
    def before(other: Time): Boolean = {
      if ((hours < other.hours) || (hours == other.hours && minutes < other.minutes)) true
      else false
    }
  }

  var t = new Time(22, 34)
  println("Time is: " + t.hours + ":" + t.minutes)
  t.before(new Time(22, 35))
  !t.before(new Time(22, 33))

  /**
   * 4. Reimplement the Time class from the preceding exercise so that the internal
   * representation is the number of minutes since midnight (between 0 and
   * 24 x 60 - 1). Do not change the public interface. That is, client code should be
   * unaffected by your change.
   */
  class TimeAlt(h: Int, m: Int) {
    private val minutesSinceMidnight = h * 60 + m

    def hours = minutesSinceMidnight / 60

    def minutes = minutesSinceMidnight % 60
    def before(other: TimeAlt): Boolean = {
      if (minutesSinceMidnight < other.minutesSinceMidnight) true
      else false
    }
  }

  var u = new TimeAlt(22, 34)
  println("Time is: " + u.hours + ":" + u.minutes)
  u.before(new TimeAlt(22, 35))
  !u.before(new TimeAlt(22, 33))

  /**
   * 5. Make a class Student with read-write JavaBeans properties name (of type String)
   * and id (of type Long). What methods are generated? (Use javap to check.) Can
   * you call the JavaBeans getters and setters in Scala? Should you?
   */
  import scala.beans.BeanProperty

  // generates the methods getId, getName, setId, setName, id, name
  class Student(@BeanProperty var name: String, @BeanProperty var id: Long)

  var s = new Student("Foo", 3)
  s.getId
  s.getName
  s.setId(4)
  s.setName("Bar")
  s.id
  s.name
  /**
   * 6. In the Person class of Section 5.2, "Properties with Getters and Setters”
   * on page 49, provide a primary constructor that turns negative ages to 0.
   */
  class Person(var age: Int) {
    if (age < 0) age = 0
  }

  var p = new Person(-1)
  p.age

  /**
   * 7. Write a class Person with a primary constructor that accepts a string containing
   * a first name, a space, and a last name, such as new Person("Fred Smith"). Supply
   * read-only properties firstName and lastName. Should the primary constructor
   * parameter be a var, a val, or a plain parameter? Why?
   */
  class PersonWithName(val fullName: String) {
    val (firstName, lastName) = (fullName.takeWhile(_ != ' '), fullName.dropWhile(_ != ' ').drop(1))
  }

  var q = new PersonWithName("Fred Smith")
  q.firstName
  q.lastName
  /**
   * 8. Make a class Car with read-only properties for manufacturer, model name,
   * and model year, and a read-write property for the license plate. Supply four
   * constructors. All require the manufacturer and model name. Optionally,
   * model year and license plate can also be specified in the constructor. If not,
   * the model year is set to -1 and the license plate to the empty string. Which
   * constructor are you choosing as the primary constructor? Why?
   */
  class Car(val manufacturer: String, val model: String) {
    private var privateYear: Int = -1
    var license_plate: String = ""

    def year = privateYear

    def this(manufacturer: String, model: String, year: Int) {
      this(manufacturer, model)
      this.privateYear = year
    }

    def this(manufacturer: String, model: String, license_plate: String) {
      this(manufacturer, model)
      this.license_plate = license_plate
    }

    def this(manufacturer: String, model: String, year: Int, license_plate: String) {
      this(manufacturer, model, license_plate)
      this.privateYear = year
    }
  }

  var car = new Car("Renault", "Espace")
  car.manufacturer
  car.model
  car.year
  car.license_plate
  var car2 = new Car("Renault", "Espace", 1943)
  car2.manufacturer
  car2.model
  car2.year
  car2.license_plate
  var car3 = new Car("Renault", "Espace", "AA-11-BB")
  car3.manufacturer
  car3.model
  car3.year
  car3.license_plate
  var car4 = new Car("Renault", "Espace", 1943, "AA-11-BB")
  car4.manufacturer
  car4.model
  car4.year
  car4.license_plate
  /**
   * 9. Reimplement the class of the preceding exercise in Java, C#, or C++ (your
   * choice). How much shorter is the Scala class?
   */
  /**
   * 10. Consider the class
   *
   * class Employee(val name: String, var salary: Double) {
   * def this() { this("John Q. Public", 0.0) }
   * }
   *
   * Rewrite it to use explicit fields and a default primary constructor. Which form
   * do you prefer? Why?
   */

  class Employee(val name: String, var salary: Double) {
    def this() {
      this("John Q. Public", 0.0)
    }
  }

  // I prefer this, as it is much shorter and
  class EmployeeAlt(val name: String = "John Q. Public", var salary: Double = 0.0)

  var e = new Employee()
  e.name
  e.salary

  var ea = new EmployeeAlt()
  ea.name
  ea.salary
}
