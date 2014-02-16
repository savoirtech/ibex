package com.savoirtech.ibex.api

import org.apache.camel.impl.DefaultComponentResolver
import org.apache.camel.spi.ComponentResolver
import org.apache.camel.CamelContext

class IbexComponentResolver extends ComponentResolver {

  val componentResolver = new DefaultComponentResolver

  def resolveComponent(component: String, camelContext: CamelContext) = {
    componentResolver.resolveComponent(component, camelContext)
  }
}
