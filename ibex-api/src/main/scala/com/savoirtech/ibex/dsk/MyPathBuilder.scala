package com.savoirtech.ibex.dsk

abstract class PathBuilder[+Self] {
  this: Self =>

  def from(uri: String): Self = {
    println("from(" + uri + ")")
    this
  }

  def to(uri: String): Self = {
    println("to(" + uri + ")")
    this
  }
}

trait EIP[+Self] extends PathBuilder[Self] {

  this: Self =>

  def log(msg: String): Self = {
    println("log(" + msg + ")")
    this
  }
}

class MyPathBuilder extends PathBuilder[MyPathBuilder] with EIP[MyPathBuilder] {

}

object MyPathBuilder {
  def main(args: Array[String]): Unit = {
    val dsl = new MyPathBuilder
    dsl.from("Hello").to("World").log("Foo")
  }

}
