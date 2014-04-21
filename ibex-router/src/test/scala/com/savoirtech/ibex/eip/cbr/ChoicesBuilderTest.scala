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

import org.junit.Test
import akka.actor.ActorRef

class ChoicesBuilderTest {
  @Test
  def testEmptyChoices() {
    val builder:ChoicesBuilder = ChoicesBuilder()
    assert(builder.choices.isEmpty)
  }

  @Test
  def testWithSingleChoice() {
    val builder:ChoicesBuilder = ChoicesBuilder().when(msg => true).recipient(ActorRef.noSender)
    assert(builder.choices.size == 1)
  }

  @Test
  def testWithOtherwise() {
    val builder:ChoicesBuilder = ChoicesBuilder().when(msg => true).recipient(ActorRef.noSender).otherwise(ActorRef.noSender)
    assert(builder.choices.size == 2)
  }

}
