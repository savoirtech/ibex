package com.savoirtech.ibex.api

case class Message(body: AnyRef, headers: Map[String, AnyRef] = Map()) {

  def copy(newBody: AnyRef) = {
    new Message(newBody, headers)
  }
}
