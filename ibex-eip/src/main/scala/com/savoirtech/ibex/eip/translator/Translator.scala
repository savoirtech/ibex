package com.savoirtech.ibex.eip.translator

import com.savoirtech.ibex.api.{MessagingContext, Messenger, Message}

class Translator(f: (Message) => Message) extends AnyRef with Messenger {
  override def execute(msg: Message, context: MessagingContext): Unit = {
    context.dispatcher().send(f(msg))
  }
}
