package com.savoirtech.ibex.eip.splitter

import com.savoirtech.ibex.api.Message
import com.savoirtech.ibex.actor.IbexActor
import akka.actor.ActorRef

class Splitter(f: (Message) => List[Message], recipient: ActorRef) extends IbexActor {
  override def onMessage(msg: Message): Unit = {
    val splits: List[Message] = f(msg)
    f(msg).foreach {
      msg => recipient ! msg
    }
  }
}
