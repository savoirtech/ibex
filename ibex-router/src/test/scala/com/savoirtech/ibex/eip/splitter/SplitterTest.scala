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
    val actor = system.actorOf(Props(classOf[Splitter], (msg: Message) => msg.body.asInstanceOf[String].map(c => msg.copy(c.toString)).toList, testActor), "splitter")
    actor ! Message("abc")
    expectMsg(Message("a", Map("BATCH_SIZE" -> 3, "BATCH_INDEX" -> 0)))
    expectMsg(Message("b", Map("BATCH_SIZE" -> 3, "BATCH_INDEX" -> 1)))
    expectMsg(Message("c", Map("BATCH_SIZE" -> 3, "BATCH_INDEX" -> 2)))
  }


}