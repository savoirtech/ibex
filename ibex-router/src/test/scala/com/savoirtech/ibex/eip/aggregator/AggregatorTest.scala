package com.savoirtech.ibex.eip.aggregator

import com.savoirtech.ibex.test.AkkaTestCase
import org.junit.Test
import akka.actor.{Props, ActorRef}
import com.savoirtech.ibex.api.Message
import scala.Option
import scala.concurrent.duration._


class AggregatorTest extends AkkaTestCase {

  val concat = (aggregated: Option[Message], current: Message) => {
    aggregated match {
      case None => current
      case Some(msg) => msg.copy(msg.body.asInstanceOf[String] + current.body.asInstanceOf[String])
    }
  }

  @Test
  def testWithNoMessages() {
    val actor = system.actorOf(Props(classOf[Aggregator], (msg:Message)=>true, concat, testActor, 250 milliseconds))
    expectNoMsg(1 second)
  }

  @Test
  def testWithAggregatedMessages() {
    val actor = system.actorOf(Props(classOf[Aggregator], (msg:Message)=>true, concat, testActor, 500 milliseconds))
    actor ! Message("a")
    actor ! Message("b")
    actor ! Message("c")
    expectMsg(Message("abc"))
  }
}
