/*
 * Copyright (C) 2014 Savoir Technologies, Inc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
    val actor = system.actorOf(Props(classOf[Aggregator], concat, testActor, 250 milliseconds))
    expectNoMsg(1 second)
  }

  @Test
  def testWithAggregatedMessages() {
    val actor = system.actorOf(Props(classOf[Aggregator], concat, testActor, 500 milliseconds))
    actor ! Message("a")
    actor ! Message("b")
    actor ! Message("c")
    expectMsg(Message("abc"))
  }
}
