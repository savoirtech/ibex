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

import actors.EventSpitter
import akka.routing.{DefaultResizer, RoundRobinRouter}
import akka.actor._
import org.apache.camel.impl.{DefaultCamelContext, DefaultExchange}
import akka.event.Logging
import scala.Some
import akka.actor.DeadLetter
import akka.util.Timeout
import akka.pattern.ask
import com.savoirtech.ibex.api.IbexProcessActor
import com.savoirtech.ibex.Process
import concurrent.ExecutionContext
import ExecutionContext.Implicits.global

object SillyMain extends App {

  override def main(args: Array[String]) {
    val system = ActorSystem("ibexeip")

    val deadletter = system.actorOf(Props[EventSpitter])
    system.eventStream.setLogLevel(Logging.DebugLevel)
    system.eventStream.subscribe(deadletter, classOf[DeadLetter])

    val resizer = DefaultResizer(lowerBound = 5, upperBound = 100)
    val router3 = system.actorOf(Props[Splitter].withRouter(RoundRobinRouter(resizer = Some(resizer))), "eip!splitter")

    val myRouter = system.actorOf(Props(new IbexProcessActor(new DemoProcessor(), "akka://ibexeip/user/eip!splitter")))
    val camelContext = new DefaultCamelContext()
    /*  println("Path " + myRouter.path)
    val start = System.currentTimeMillis()

    1 to 100 foreach {
      val message = new DefaultMessage

      i ⇒ message
      val ex = new DefaultExchange(camelContext)
      ex.setIn(message)

      implicit val timeout2 = Timeout(5)
      myRouter ! Process(ex)


      //system.actorFor("akka://ibexeip/user/eip!splitter") ! ex
    }*/
    val testR = new IbexRouterBuilder

    testR.configure();

    val routes = testR.getRouteCollection

    // camelContext.addRoutes(testR)
    val routeList = routes.getRoutes
    val itr = routeList.iterator()

    while (itr.hasNext) {
      val aRoute = itr.next();
      println("Froms " + aRoute.getInputs)
      println("Outputs " + aRoute.getOutputs)
      println("Route " + aRoute)
    }



    implicit val timeout2 = Timeout(5000)
    val exchange = new DefaultExchange(camelContext)
    exchange.getIn.setHeader("FROM", myRouter.path)


    //Traditional Camel like blocking request reply.
    val result = myRouter ? Process(exchange)
    //result.onComplete {
      //case Right(output) ⇒ println("Received " + output)
      //case Left(failure) ⇒ println("Failure " + failure)
    //}

    //Thread.sleep(500)
    //println("This is the actual result " + result.isCompleted)
    //println("The resulting message " + result.value)
  }
}

//sys.exit()}}}


