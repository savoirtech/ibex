package com.savoirtech.ibex.eip.wiretap

import com.savoirtech.ibex.actor.IbexActor
import com.savoirtech.ibex.api.Message
import akka.actor.ActorRef

class Wiretap(recipient: ActorRef, wiretapRecipient: ActorRef) extends IbexActor {
  override def onMessage(msg: Message): Unit = {
    recipient ! msg
    wiretapRecipient ! msg
  }
}
