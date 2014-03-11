import scala.slick.jdbc.JdbcBackend.Database

/** A simple example that uses plain SQL queries against an in-memory
  * H2 database. The example data comes from Oracle's JDBC tutorial at
  * http://download.oracle.com/javase/tutorial/jdbc/basics/tables.html. */
object PlainSQL extends App with Interpolation with Transfer with BuildQuery {

  // Open a database connection
  Database.forURL("jdbc:h2:mem:hello", driver = "org.h2.Driver") withSession { implicit session =>

    createSuppliers
    createCoffees
    insertSuppliers
    insertCoffees
    printAll
    printParameterized

    println("Coffee Colombian: " + coffeeByName("Colombian"))

    val rows = deleteCoffee("Colombian")
    println(s"Deleted $rows rows")
    println("Coffee Colombian: " + coffeeByName("Colombian"))
  }
}
