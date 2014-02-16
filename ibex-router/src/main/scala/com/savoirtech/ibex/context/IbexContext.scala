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

package com.savoirtech.ibex.context

import org.apache.camel.CamelContext
import org.apache.camel.spi.Registry
import org.apache.camel.builder.RouteBuilder

/**
 * Base class that will take a Apache Camel route definition, parse this
 * according to the existing JAXB based (which in turn will allow us to parse via blueprint)
 * the route information.
 *
 * Out of this we will orchestrate an actor chain of pooled actors per VERB in the syntax
 * Pure connectivity can just as easily be handled via existing camel-akka, it already delivers
 * us an actor ref design, so homerun.
 *
 * For a "processor" all we really need is for the end user to be able to extend a class that allows for
 * exchange processing, then dump this into a new ref.
 *
 */
trait IbexContext extends CamelContext {

  def addRoutes(routeBuilder: RouteBuilder) {
    //Build all the route fragments.
    routeBuilder.configure
    routeBuilder.getRouteCollection

    //Build an actor chain for each route in the context.

    val routes = routeBuilder.getRouteCollection

   /**
    for (route <- routes) {
      //Build the routes.





    }
    */
  }

  def start() {
  }

  def getRegistry: Registry = {
    return null
  }
}
