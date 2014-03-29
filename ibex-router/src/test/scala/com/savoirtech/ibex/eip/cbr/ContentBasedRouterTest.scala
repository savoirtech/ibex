package com.savoirtech.ibex.eip.cbr

import com.savoirtech.ibex.test.AkkaTestCase
import org.junit.Test
import akka.actor.{ActorRef, Props}
import com.savoirtech.ibex.api.Message

class ContentBasedRouterTest extends AkkaTestCase {
  @Test
  def testWithMatchingMessage() {
    val actor = system.actorOf(Props(classOf[ContentBasedRouter], List(Choice(msg => true, testActor))), "choice")
    val msg = Message(body="Hello, World!")
    actor ! msg
    expectMsg(msg)
  }

  @Test
  def testWithMismatchAndOtherwise() {
    val actor = system.actorOf(Props(classOf[ContentBasedRouter], List(Choice(msg => false, ActorRef.noSender), Choice.otherwise(testActor))), "choice")
    val msg = Message(body="Hello, World!")
    actor ! msg
    expectMsg(msg)
  }
}
