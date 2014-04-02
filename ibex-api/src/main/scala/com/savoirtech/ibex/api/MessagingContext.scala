package com.savoirtech.ibex.api

trait MessagingContext {
  def dispatcher(): MessageDispatcher

  def originalMessage(): Message
}
