package com.savoirtech.ibex.eip.cbr

import com.savoirtech.ibex.api.Message
import akka.actor.ActorRef

case class Choice(predicate: (Message) => Boolean, recipient: ActorRef) {

}

object Choice {
  def otherwise(recipient:ActorRef):Choice = {
    Choice(msg => true, recipient)
  }
}
