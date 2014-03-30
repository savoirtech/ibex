package com.savoirtech.ibex.eip.filter

import com.savoirtech.ibex.test.AkkaTestCase
import org.junit.Test
import akka.actor.Props
import com.savoirtech.ibex.api.Message
import scala.concurrent.duration._

class FilterTest extends AkkaTestCase {
  @Test
  def testWithAcceptedMessage() {
    val actor = system.actorOf(Props(classOf[Filter], (msg:Message) => true, testActor), "filter")
    actor ! Message("Hello!")
    expectMsg(Message("Hello!"))
  }

  @Test
  def testWithFilteredMessage() {
    val actor = system.actorOf(Props(classOf[Filter], (msg:Message) => false, testActor), "filter")
    actor ! Message("Hello!")
    expectNoMsg(500 milliseconds)
  }
}
