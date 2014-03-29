package com.savoirtech.ibex.actor

import akka.actor.{Actor, ActorLogging}
import com.savoirtech.ibex.api.Message

abstract class IbexActor extends Actor with ActorLogging {

  def onMessage(msg: Message): Unit

  override def receive: Receive = {
    case msg: Message => onMessage(msg)
  }
}
