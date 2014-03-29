package com.savoirtech.ibex.eip.cbr

import akka.actor.{ActorLogging, Actor}
import com.savoirtech.ibex.api.Message

class ContentBasedRouter(choices: List[Choice]) extends Actor with ActorLogging {
  override def receive: Receive = {
    case msg: Message =>
      choices.find(choice => choice.predicate(msg)) match {
        case None =>
          log.error("Unable to find choice matching message {}.", msg)
          unhandled(msg)
        case Some(choice: Choice) => choice.recipient ! msg
      }
  }
}
