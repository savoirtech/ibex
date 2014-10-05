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

package com.savoirtech.ibex.eip.splitter

import akka.actor.Props
import com.savoirtech.ibex.Message
import com.savoirtech.ibex.test.AkkaTestCase

import scala.concurrent.duration._

class SplitterTest extends AkkaTestCase {
  "A Splitter" should {
    "do nothing when there are no splits" in {
      within(200 milliseconds) {
        val message = Message("Hello")
        val splitter = system.actorOf(Props(classOf[Splitter], (msg: Message) => Nil, testActor))
        splitter ! message
        expectNoMsg()
      }
    }

    "send all proceed Traversal with all splits" in {
      within(200 milliseconds) {
        val message = Message("abc")
        val f = (msg: Message) => msg.body.toString.toList.map((c: Char) => Message(c.toString))
        val splitter = system.actorOf(Props(classOf[Splitter], f, testActor))
        splitter ! message
        expectMsg(Message("a").withHeader("BATCH_SIZE", Int.box(3)).withHeader("BATCH_INDEX", Int.box(0)))
        expectMsg(Message("b").withHeader("BATCH_SIZE", Int.box(3)).withHeader("BATCH_INDEX", Int.box(1)))
        expectMsg(Message("c").withHeader("BATCH_SIZE", Int.box(3)).withHeader("BATCH_INDEX", Int.box(2)))
        expectNoMsg()
      }
    }
  }
}
