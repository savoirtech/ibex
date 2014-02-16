/*
 * Copyright (C) 2012  Savoir Technologies, Inc
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

package com.savoirtech.ibex.api

import akka.actor.Actor
import org.apache.camel.Processor
import com.savoirtech.ibex.Process

class IbexProcessActor(processor: Processor, nextHop: String) extends Actor {

  def inOut:Boolean = false

  def receive = {
    case Process(exchange) =>
      processor.process(exchange);
      println(this.getClass + " nextHop " + nextHop)
      if (nextHop.isEmpty) {
        sender tell (exchange)
        println(this.getClass + " Received message and modified it. -> '%s' in actor %s".format(exchange, self.path))
      } else {
        println(this.getClass + " Found a message I need to re-route, to " + nextHop + " then I need to tell the parent.")
        val nextActor = this.context.actorFor(nextHop)
        nextActor.forward(Process(exchange))
      }
  }
}
