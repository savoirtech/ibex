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

import org.apache.camel.{Processor, Exchange}
import com.savoirtech.ibex.api.IbexProcessorWithActor

class DemoProcessorWithActor extends IbexProcessorWithActor {

  def process(ex: Exchange) {
    ex.getIn.setBody("This is a new boday!!!")
  }
}
