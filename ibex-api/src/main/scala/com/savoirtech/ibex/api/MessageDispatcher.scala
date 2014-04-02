package com.savoirtech.ibex.api

import scala.concurrent.Future

trait MessageDispatcher {
  def send(msg: Message): Unit

  def send(msg: Message, recipient: String): Unit

  def request(msg: Message): Future[Message]

  def request(msg: Message, recipient: String): Future[Message]
}
