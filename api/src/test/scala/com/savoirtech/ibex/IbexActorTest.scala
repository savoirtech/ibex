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

package com.savoirtech.ibex

import akka.actor.{ActorRef, Props, ReceiveTimeout, UnhandledMessage}
import com.savoirtech.ibex.test.AkkaTestCase

import scala.concurrent.duration._


class IbexActorTest extends AkkaTestCase {
  "An IbexActor" should {
    "call onMessage() when receiving an Ibex message" in {
      within(200 milliseconds) {
        val actor = system.actorOf(Props(classOf[TestActor], testActor))
        actor ! Message("foo")
        expectMsg(OnMessage)
      }
    }

    "call onTimeout() when receiving a ReceiveTimeout" in {
      within(200 milliseconds) {
        val actor = system.actorOf(Props(classOf[TestActor], testActor))
        expectMsg(OnTimeout)
      }
    }

    "call unhandled when receiving non-Ibex message" in {
      within(200 milliseconds) {
        val actor = system.actorOf(Props(classOf[TestActor], testActor))
        actor ! "foo"
        system.eventStream.subscribe(testActor, classOf[UnhandledMessage])
        UnhandledMessage("foo", testActor, actor)
      }
    }
  }


}

class TestActor(recipient: ActorRef) extends IbexActor {

  context.setReceiveTimeout(100 milliseconds)

  override def onMessage(msg: Message): Unit = recipient ! OnMessage

  override def onTimeout(timeout: ReceiveTimeout): Unit = recipient ! OnTimeout
}

sealed trait IbexActorMessage

case object OnMessage extends IbexActorMessage

case object OnTimeout extends IbexActorMessage
