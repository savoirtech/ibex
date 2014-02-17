package com.savoirtech.ibex.router.actors

import com.savoirtech.ibex.dsl.DSL
import com.savoirtech.ibex.api.IbexProcessor
import org.apache.camel.Exchange
import scala.collection.immutable.Stack
import java.lang.String

abstract class IbexRouteBuilder extends DSL {

  //Set a route id
  //Attach to a particular AKKA chain
  //Register and monitor

  def configure()

  val stack = new Stack[DSL]

  def from(uri: String) = stack.top.from(uri)

  def to(uri: String) = stack.top.to(uri)

  def process(function: Exchange => Unit) = stack.top.process(function)


  //This would instantiate an IbexProcessor, hide that in an
  //actor and then tell it what the next hop would be as we traverse the
  //chain.

  def process(processor: IbexProcessor) = stack.top.process(processor)

  def -->(uri: String) = stack.top.to(uri)
}
