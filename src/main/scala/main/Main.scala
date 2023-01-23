package main

import dao.BooksDetails
import model.Books

import scala.io.StdIn.readLine

object Main {
  def main(args: Array[String]): Unit = {
val a= new BooksDetails
val b=a.getConnection()
    val c=a.createStatement(b)
    val o1=Books(111,"Bimal Jalal","The India Story","Chicago Book Clinic","Fifth",78999,"III","6-7-22",799,"Issued")
    val o2=Books(156,"Anuradha Roy","The Earthspinner","Penguin India","First",30765,"II","8-1-23",567,"Un-issued")
    a.add(c,o1)
    a.add(c,o2)

    a.read(c)
    println("Enter the name of columns to update: ")
    val u1: String = readLine()
    println("Enter the update Value")
    val u2: Any = readLine()
    println("Mention the condition to Update where")
    val i: String = readLine()
    println("The column value is: ")
    val k: Any = readLine()
    val t2 = Map(u1 -> u2)
    val t4 = Map(i -> k)
    a.update(c,t2,t4)

    println("Enter the name of column you want to delete")
    val j1 = readLine()
    println("Mention the condition for")
    val j2: Any = readLine()
    val t3 = Map(j1 -> j2)
    a.delete(c,t3)
  }
}
