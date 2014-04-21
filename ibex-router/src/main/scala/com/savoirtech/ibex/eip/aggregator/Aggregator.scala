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

package com.savoirtech.ibex.eip.aggregator

import com.savoirtech.ibex.actor.IbexActor
import com.savoirtech.ibex.api.Message
import akka.actor.{ActorRef, ReceiveTimeout}
import scala.concurrent.duration.Duration

class Aggregator(aggregation: (Option[Message], Message) => Message, recipient: ActorRef, timeout: Duration) extends IbexActor {

  context.setReceiveTimeout(timeout)

  var aggregated: Option[Message] = None

  override def onTimeout(timeout: ReceiveTimeout): Unit = {
    aggregated match {
      case Some(msg) => recipient ! msg
      case _ =>
    }
  }

  override def onMessage(msg: Message): Unit = {
    aggregated = Some(aggregation.apply(aggregated, msg))
  }
}
