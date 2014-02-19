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

import org.apache.camel.{Processor, Exchange}
import akka.actor.Actor
import com.savoirtech.ibex.Process

abstract class IbexProcessorWithActor extends Actor with Processor {

  var nextActor: String = null;

  def receive = {

    case Process(exchange) =>
      println("IbexProcessor -- Received message. '%s' in actor %s".format(exchange, self.path))

      process(exchange);
      if (nextActor == null) {
        sender tell (exchange,self)
      } else {
        println("IbexProcessor -- Found a message I need to re-route, to " + nextActor + " then I need to tell the parent.")
        val nextHop = this.context.actorFor(nextActor)
        nextHop.tell(exchange,self)
      }

      println("IbexProcessor -- Received message and modified it. -> '%s' in actor %s".format(exchange, self.path))
  }

  def process(exchange: Exchange)
}
