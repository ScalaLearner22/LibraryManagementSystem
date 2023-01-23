package booksImplementation

import database.PostgresConnector
import model.Books

import java.sql.{Connection, ResultSet, Statement}

class BooksDetails extends PostgresConnector{
  classOf[org.postgresql.Driver]

  def createStatement(conn: Connection): Statement = {
    conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
  }

  def addIntoDb(stm: Statement, b1: Books): Int = {
    val sqlInsert = s"INSERT INTO Books (accno, author, title, publication, edition, no_of_copies, volumn, date_pur, price, status) VALUES (${b1.accno},'${b1.author}','${b1.title}','${b1.publication}', '${b1.edition}', ${b1.no_of_copies}, '${b1.volumn}', '${b1.date_pur}', ${b1.price}, '${b1.status}')"
    stm.executeUpdate(sqlInsert)
  }

  def read(stm: Statement): List[Books] = {
    val sqlRead = "SELECT * from libraryManagement"
    val result = stm.executeQuery(sqlRead)

    def toIterator(resultSet: ResultSet): Iterator[ResultSet] = {
      new Iterator[ResultSet] {
        def hasNext = resultSet.next()

        def next() = resultSet
      }
    }

    toIterator(result).map {
      o => Books(o.getInt("accno"), o.getString("author"), o.getString("title"), o.getString("publication"), o.getString("edition"), o.getInt("no_of_copies"), o.getString("volumn"), o.getString("date_pur"), o.getInt("price"), o.getString("status"))

    }.toList
  }

  def updateIntoDb(stm: Statement, set1: Map[String, Any], condition1: Map[String, Any]): Int = {
    val sqlUpdate = s"UPDATE Student SET ${set1.map(p => s"${p._1}='${p._2}'").mkString(" , ")} WHERE ${condition1.map(p => s"${p._1}='${p._2}'").mkString(" AND ")}"
  stm.executeUpdate(sqlUpdate)
  }

  def deleteIntoDb(stm: Statement, condition2: Map[String, Any]): Int = {
    val sqlDelete = s"DELETE FROM Student WHERE ${condition2.map(p => s"${p._1}='${p._2}'").mkString(" AND ")}"
    stm.executeUpdate(sqlDelete)
  }


}
