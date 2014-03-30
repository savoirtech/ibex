package com.savoirtech.ibex.eip.aggregator

import com.savoirtech.ibex.actor.IbexActor
import com.savoirtech.ibex.api.Message
import akka.actor.{ActorRef, ReceiveTimeout}
import scala.concurrent.duration.Duration

class Aggregator(correlator: (Message) => AnyVal, aggregation: (Option[Message], Message) => Message, recipient: ActorRef, timeout: Duration) extends IbexActor {

  context.setReceiveTimeout(timeout)

  var aggregated: Option[Message] = None

  override def receive: Receive = {
    case ReceiveTimeout =>
      aggregated match {
        case Some(msg) => recipient ! msg
        case _ =>
      }
    case msg: Message =>
      aggregated = Some(aggregation.apply(aggregated, msg))
    case _ => super.receive
  }

  override def onMessage(msg: Message): Unit = {
    // Not used!
  }
}
