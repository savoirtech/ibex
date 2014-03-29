package com.savoirtech.ibex.eip.cbr

import com.savoirtech.ibex.api.Message
import com.savoirtech.ibex.actor.IbexActor

class ContentBasedRouter(choices: List[Choice]) extends IbexActor {

  override def onMessage(msg: Message): Unit = {
    choices.find(choice => choice.predicate(msg)) match {
      case None =>
        log.error("Unable to find choice matching message {}.", msg)
        unhandled(msg)
      case Some(choice: Choice) => choice.recipient ! msg
    }
  }
}
