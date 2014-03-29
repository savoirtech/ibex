package com.savoirtech.ibex.eip.splitter

import com.savoirtech.ibex.test.AkkaTestCase
import org.junit.Test
import akka.actor.Props
import com.savoirtech.ibex.api.Message
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit

class SplitterTest extends AkkaTestCase {

  @Test
  def testWithNoSplits() {
    val actor = system.actorOf(Props(classOf[Splitter], (msg: Message) => Nil, testActor), "splitter")
    actor ! Message("Hello")
    expectNoMsg(Duration(1, TimeUnit.SECONDS))
  }

  @Test
  def testWithSplits() {
    val actor = system.actorOf(Props(classOf[Splitter], (msg:Message) => msg.body.asInstanceOf[String].map(c => msg.copy(c.toString)).toList, testActor), "splitter")
    actor ! Message("abc")
    expectMsg(Message("a"))
    expectMsg(Message("b"))
    expectMsg(Message("c"))
  }


}