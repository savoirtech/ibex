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
