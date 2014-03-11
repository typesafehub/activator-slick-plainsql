import scala.slick.jdbc.StaticQuery
import scala.slick.jdbc.JdbcBackend.Session

trait BuildQuery { this: PlainSQL.type =>

  def createCoffees(implicit session: Session): Unit = {
    StaticQuery.updateNA("""create table coffees(
      name varchar not null,
      sup_id int not null,
      price double not null,
      sales int not null,
      total int not null,
      foreign key(sup_id) references suppliers(id))""").execute
  }

  def insertSuppliers(implicit session: Session): Unit = {
    // Insert some suppliers
    (StaticQuery.u + "insert into suppliers values(101, 'Acme, Inc.', '99 Market Street', 'Groundsville', 'CA', '95199')").execute
    (StaticQuery.u + "insert into suppliers values(49, 'Superior Coffee', '1 Party Place', 'Mendocino', 'CA', '95460')").execute
    (StaticQuery.u + "insert into suppliers values(150, 'The High Ground', '100 Coffee Lane', 'Meadows', 'CA', '93966')").execute
  }

  def insertCoffees(implicit session: Session): Unit = {
    def insert(c: Coffee) = (StaticQuery.u + "insert into coffees values (" +? c.name +
      "," +? c.supID + "," +? c.price + "," +? c.sales + "," +? c.total + ")").execute

    // Insert some coffees. The SQL statement is the same for all calls:
    // "insert into coffees values (?,?,?,?,?)"
    Seq(
      Coffee("Colombian", 101, 7.99, 0, 0),
      Coffee("French_Roast", 49, 8.99, 0, 0),
      Coffee("Espresso", 150, 9.99, 0, 0),
      Coffee("Colombian_Decaf", 101, 8.99, 0, 0),
      Coffee("French_Roast_Decaf", 49, 9.99, 0, 0)
    ).foreach(insert)
  }

  def printAll(implicit session: Session): Unit = {
    // Iterate through all coffees and output them
    println("Coffees:")
    StaticQuery.queryNA[Coffee]("select * from coffees") foreach { c =>
      println("* " + c.name + "\t" + c.supID + "\t" + c.price + "\t" + c.sales + "\t" + c.total)
    }
  }

  val namesByPrice = StaticQuery.query[Double, (String, String)]("""
    select c.name, s.name
    from coffees c, suppliers s
    where c.price < ? and s.id = c.sup_id
  """)

  val supplierById = StaticQuery[Int, Supplier] + "select * from suppliers where id = ?"

  def printParameterized(implicit session: Session): Unit = {
    // Perform a join to retrieve coffee names and supplier names for
    // all coffees costing less than $9.00
    println("Parameterized StaticQuery:")
    val l2 = namesByPrice(9.0).list // Read the result set into a List
    for (t <- l2) println("* " + t._1 + " supplied by " + t._2)

    // Append to a StaticQuery
    println("Supplier #49: " + supplierById(49).first)
  }
}
