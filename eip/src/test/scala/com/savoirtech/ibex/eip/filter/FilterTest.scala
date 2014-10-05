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

import akka.actor.Props
import com.savoirtech.ibex.Message
import com.savoirtech.ibex.test.AkkaTestCase

import scala.concurrent.duration._

class FilterTest extends AkkaTestCase {
  "A Filter" should {
    "send message when predicate matches" in {
      within(200 milliseconds) {

        val filter = system.actorOf(Props(classOf[Filter], (msg: Message) => msg.body === "testme", testActor))

        filter ! Message("testme")
        filter ! Message("badinput")
        expectMsg(Message("testme"))

      }
    }
  }
}
