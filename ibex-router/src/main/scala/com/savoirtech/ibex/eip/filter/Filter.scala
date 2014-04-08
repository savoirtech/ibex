package com.savoirtech.ibex.eip.filter

import com.savoirtech.ibex.actor.IbexActor
import com.savoirtech.ibex.api.Message
import akka.actor.ActorRef

class Filter(predicate: (Message) => Boolean, recipient: ActorRef) extends IbexActor {
  override def onMessage(msg: Message): Unit = {
    if (predicate(msg)) {
      recipient ! msg
    }
  }
}
