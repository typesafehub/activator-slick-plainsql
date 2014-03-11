import scala.slick.jdbc.StaticQuery.interpolation
import scala.slick.jdbc.JdbcBackend.Session

trait Interpolation { this: PlainSQL.type =>

  def createSuppliers(implicit session: Session): Unit = {
    sqlu"""create table suppliers(
      id int not null primary key,
      name varchar not null,
      street varchar not null,
      city varchar not null,
      state varchar not null,
      zip varchar not null)""".execute
  }

  def coffeeByName(name: String)(implicit session: Session): Option[Coffee] =
    sql"select * from coffees where name = $name".as[Coffee].firstOption

  def deleteCoffee(name: String)(implicit session: Session) =
    sqlu"delete from coffees where name = $name".first
}
