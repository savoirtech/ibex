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

package com.savoirtech.ibex.eip.cbr

import akka.actor.{ActorRef, Props, UnhandledMessage}
import com.savoirtech.ibex.Message
import com.savoirtech.ibex.test.AkkaTestCase

import scala.concurrent.duration._


class ContentBasedRouterTest extends AkkaTestCase {
  "A ContentBasedRouter" should {
    "send message to first-matched choice" in {
      within(200 milliseconds) {

        val choices = List(
          Choice(msg => msg.body === "first", ActorRef.noSender),
          Choice(msg => msg.body === "second", testActor)
        )
        val cbr = system.actorOf(Props(classOf[ContentBasedRouter], choices))
        val message = Message("second")
        cbr ! message
        expectMsg(message)

      }
    }

    "call unhandled when no matching choice found" in {
      within(200 milliseconds) {

        val choices = List(
          Choice(msg => false, testActor)
        )
        val cbr = system.actorOf(Props(classOf[ContentBasedRouter], choices))
        val message = Message("unknown")
        system.eventStream.subscribe(testActor, classOf[UnhandledMessage])
        cbr ! message
        expectMsg(UnhandledMessage(message, testActor, cbr))
      }
    }
  }
}
