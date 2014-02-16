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

package com.savoirtech.ibex.router

import akka.actor.Actor
import org.apache.camel.{Exchange, Processor}
import com.savoirtech.ibex.{IbexExchange, Process}

abstract class SimpleProcessor extends Actor with Processor {

  def receive = {

    case Process(exchange) =>
      println(this.getClass + " Received message. '%s' in actor %s".format(exchange, self.path.address))
      println(this.getClass + " The sender " + sender)
      println(this.getClass + " Received message and modified it. -> '%s' in actor %s".format(exchange, self.path.name))
      process(exchange)
      sender tell (exchange,self)
  }

  //Work on the exchange.
  def process(exchange: Exchange)
}
