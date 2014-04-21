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

package com.savoirtech.ibex.eip.wiretap

import com.savoirtech.ibex.test.StepTestCase
import akka.actor.Props
import com.savoirtech.ibex.api.Message
import scala.concurrent.duration._

class WiretapTest extends StepTestCase {

  "Wiretap" should "route message to the wiretap path" in {

    within(200 milliseconds) {
      val wiretapPath = newPath()
      val wiretap = system.actorOf(Props(classOf[Wiretap], wiretapPath))

      val message: Message = Message("Hello")
      wiretap ! newTraversal(message)

      expectMsg(Proceed(message))
      expectMsg(Send(wiretapPath, message))
    }

  }


}
