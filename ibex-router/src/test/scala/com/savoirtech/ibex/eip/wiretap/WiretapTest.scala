package com.savoirtech.ibex.eip.wiretap

import com.savoirtech.ibex.test.AkkaTestCase
import org.junit.Test
import akka.actor.Props
import com.savoirtech.ibex.api.Message

class WiretapTest extends AkkaTestCase {

  @Test
  def testWiretap() {
    val actor = system.actorOf(Props(classOf[Wiretap], testActor, testActor))
    val input: Message = Message("Hello!")
    actor ! input
    expectMsg(input)
    expectMsg(input)

  }
}
