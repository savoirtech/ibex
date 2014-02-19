package com.savoirtech.ibex.dsl

import org.apache.camel.Processor
import com.savoirtech.ibex.api.{IbexProcessorWithActor, IbexProcessActor}

abstract class AbstractProcessingDefinitions  {

  implicit def process(processor: IbexProcessorWithActor) = {
    new IbexProcessActor(processor, null)
  }
}
