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

package com.savoirtech.ibex.eip.splitter

import com.savoirtech.ibex.api.Message
import com.savoirtech.ibex.actor.IbexActor
import akka.actor.ActorRef

class Splitter(f: (Message) => List[Message], recipient: ActorRef) extends IbexActor {
  override def onMessage(msg: Message): Unit = {
    val splits: List[Message] = f(msg)
    (0 until splits.length).foreach {
      i =>
        val message = splits(i)
        recipient ! message.withHeader("BATCH_SIZE", splits.size).withHeader("BATCH_INDEX", i)
    }
  }
}
