package database

import java.sql.Connection
import java.util.Properties

trait DbConnection {
  def getConnection():Connection
}

trait PostgresConnector extends DbConnection {

  //TODO: Need to make Connection String configurable
  def getConnection(): Connection = {
    println("Postgres connector")
    import java.sql.DriverManager
    val url = "jdbc:postgresql://localhost:5432/libraryManagement"
    val props = new Properties()
    props.setProperty("user", "postgres")
    props.setProperty("password", "postgres")
    DriverManager.getConnection(url, props)
  }

}

