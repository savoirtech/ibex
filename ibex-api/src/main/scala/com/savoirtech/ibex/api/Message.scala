package com.savoirtech.ibex.api

trait Message {
  def body(): AnyRef

  def headers(): Map[String, AnyRef]
}
