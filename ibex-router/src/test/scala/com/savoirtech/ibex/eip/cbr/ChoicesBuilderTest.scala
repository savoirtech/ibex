package com.savoirtech.ibex.eip.cbr

import org.junit.Test
import akka.actor.ActorRef

class ChoicesBuilderTest {
  @Test
  def testEmptyChoices() {
    val builder:ChoicesBuilder = ChoicesBuilder()
    assert(builder.choices.isEmpty)
  }

  @Test
  def testWithSingleChoice() {
    val builder:ChoicesBuilder = ChoicesBuilder().when(msg => true).recipient(ActorRef.noSender)
    assert(builder.choices.size == 1)
  }

  @Test
  def testWithOtherwise() {
    val builder:ChoicesBuilder = ChoicesBuilder().when(msg => true).recipient(ActorRef.noSender).otherwise(ActorRef.noSender)
    assert(builder.choices.size == 2)
  }

}
