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

package com.savoirtech.ibex.api

import org.scalatest.{Matchers, WordSpec}

class MessageTest extends WordSpec with Matchers {
  "A Message" should {
    "have no headers when instantiated with just a body" in {
      val msg = Message("body")
      msg.body should be("body")
      msg.headers shouldBe empty
    }

//    "have specified headers when instantiated with them" in {
//      val msg = Message("body", Map("foo" -> "bar"))
//      msg.body should be("body")
//      msg.headers.size should be (1)
//      msg.headers("foo") should be ("bar")
//
//    }
  }
}
