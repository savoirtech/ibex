package com.savoirtech.ibex.api

trait Messenger {
  def execute(msg: Message, context: MessagingContext): Unit
}
