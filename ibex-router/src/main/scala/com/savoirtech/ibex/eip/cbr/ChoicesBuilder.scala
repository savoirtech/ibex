package com.savoirtech.ibex.eip.cbr

import com.savoirtech.ibex.api.Message
import akka.actor.ActorRef

class ChoicesBuilder(val choices: List[Choice] = Nil) {
  def when(predicate: (Message) => Boolean) = new ChoiceDefinition(predicate)

  def otherwise(recipient: ActorRef) = new ChoicesBuilder(choices :+ Choice.otherwise(recipient))

  class ChoiceDefinition(predicate: (Message) => Boolean) {

    def recipient(recipient: ActorRef): ChoicesBuilder = {
      new ChoicesBuilder(choices :+ Choice(predicate, recipient))
    }
  }

}

object ChoicesBuilder {
  def apply() = new ChoicesBuilder()
}


