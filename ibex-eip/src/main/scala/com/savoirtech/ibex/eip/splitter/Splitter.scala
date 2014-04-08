package com.savoirtech.ibex.eip.splitter

import com.savoirtech.ibex.api.{MessagingContext, Message, Messenger}

class Splitter(f: (Message) => List[Message]) extends AnyRef with Messenger {

  override def execute(msg: Message, context: MessagingContext): Unit = {
    f(msg).foreach { msg =>
      context.dispatcher().send(msg)
    }
  }
}
