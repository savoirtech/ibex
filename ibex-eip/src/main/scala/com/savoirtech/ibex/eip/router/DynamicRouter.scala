package com.savoirtech.ibex.eip.router

import com.savoirtech.ibex.api.{MessagingContext, Message, Messenger}

class DynamicRouter(f: (Message) => String) extends AnyRef with Messenger {
  override def execute(msg: Message, context: MessagingContext): Unit = {
    context.dispatcher().send(msg, f(msg))
  }
}
