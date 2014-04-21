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

import com.savoirtech.ibex.test.StepTestCase
import akka.actor.{UnhandledMessage, Props}
import com.savoirtech.ibex.api.Message
import scala.concurrent.duration._

class ContentBasedRouterTest extends StepTestCase {

  "A ContentBasedRouter" should {
    "send message to first-matched choice" in {
      within(200 milliseconds) {

        val path1 = newPath()
        val path2 = newPath()

        val choices = List(
          Choice(msg => false, path1),
          Choice(msg => true, path2)
        )
        val cbr = system.actorOf(Props(classOf[ContentBasedRouter], choices))
        val message = Message("foo")
        cbr ! newTraversal(message)
        expectMsg(PathInjection(path2, message))
      }
    }

    "call unhandled when no matching choice found" in {
      within(200 milliseconds) {
        system.eventStream.subscribe(testActor, classOf[UnhandledMessage])
        val path1 = newPath()
        val choices = List(
          Choice(msg => false, path1)
        )
        val cbr = system.actorOf(Props(classOf[ContentBasedRouter], choices))
        val message = Message("foo")
        val traversal = newTraversal(message)
        cbr ! traversal

        expectMsg(UnhandledMessage(traversal, testActor, cbr))
      }
    }
  }
}
