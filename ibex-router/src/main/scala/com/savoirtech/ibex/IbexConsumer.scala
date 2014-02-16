package com.savoirtech.ibex

import akka.actor.Actor
import akka.camel.Consumer

abstract class IbexConsumer extends Actor with Consumer {

  def receive = {
    case msg: Message => {
      /* ... */
    }
    case _ => {
      /* ... */
    }
  }
}
