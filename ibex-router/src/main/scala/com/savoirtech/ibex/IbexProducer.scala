package com.savoirtech.ibex

import akka.actor.Actor
import akka.camel.{Oneway, Producer}

abstract class IbexProducer extends Actor with Producer with Oneway {
  def endpointUri = "jms:queue:example"
}

