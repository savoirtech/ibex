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

package com.savoirtech.ibex.eip.filter

import com.savoirtech.ibex.test.StepTestCase
import com.savoirtech.ibex.api.Message
import akka.actor.Props
import scala.concurrent.duration._

class FilterTest extends StepTestCase {

  "A Filter" should "proceed current traversal when predicate matches message" in {
    within(200 milliseconds) {
      val filter = system.actorOf(Props(classOf[Filter], (message: Message) => true))
      val message = Message("Hello")
      filter ! newTraversal(message)

      expectMsg(Proceed(message))
      expectNoMsg()
    }
  }

  it should "drop the message when the predicate does not match the message" in {
    within(200 milliseconds) {
      val filter = system.actorOf(Props(classOf[Filter], (message: Message) => false))
      val message = Message("Hello")
      filter ! newTraversal(message)
      expectNoMsg()
    }
  }
}
