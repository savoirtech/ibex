package com.savoirtech.ibex.router

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.{Exchange, Processor}

class IbexRouterBuilder extends RouteBuilder {

  def configure() {

    from("seda:start").process(new Processor {
      def process(p1: Exchange) {}
    }).to("mock:end")
  }
}
