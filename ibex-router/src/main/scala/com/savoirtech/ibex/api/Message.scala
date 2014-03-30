package com.savoirtech.ibex.api


//TODO: Should we consider making the body of type Option[AnyRef] or Option[AnyVal]?  Should we ever allow None body?
case class Message(body: AnyRef, headers: Map[String, AnyRef] = Map()) {

  def copy(newBody: AnyRef) = {
    new Message(newBody, headers)
  }
}
