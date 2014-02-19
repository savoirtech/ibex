package com.savoirtech.ibex.router.actors

import com.savoirtech.ibex.dsl.{TypeHolder, AbstractProcessingDefinitions, DSL}
import com.savoirtech.ibex.api.{IbexProcessActor, IbexProcessorWithActor}
import org.apache.camel.Exchange
import scala.collection.immutable.Stack
import java.lang.String

abstract class IbexRouteBuilder extends DSL  {

  //Set a route id
  //Attach to a particular AKKA chain
  //Register and monitor
  def configure()



  /**
  sealed trait Method {
    def on[R](routeDef: RouteDef[R]): R = routeDef.withMethod(this)
  }
    */

  override def stepsInRoute {
    configure()
    super.stepsInRoute
  }




}
