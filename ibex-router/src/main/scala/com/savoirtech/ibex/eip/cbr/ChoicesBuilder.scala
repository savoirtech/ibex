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

import com.savoirtech.ibex.api.Message
import akka.actor.ActorRef

class ChoicesBuilder(val choices: List[Choice] = Nil) {
  def when(predicate: (Message) => Boolean) = new ChoiceDefinition(predicate)

  def otherwise(recipient: ActorRef) = new ChoicesBuilder(choices :+ Choice.otherwise(recipient))

  class ChoiceDefinition(predicate: (Message) => Boolean) {

    def recipient(recipient: ActorRef): ChoicesBuilder = {
      new ChoicesBuilder(choices :+ Choice(predicate, recipient))
    }
  }

}

object ChoicesBuilder {
  def apply() = new ChoicesBuilder()
}


