/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.savoirtech.ibex.dsl

;

import com.savoirtech.ibex.api.IbexProcessor
import scala.collection.mutable.ArrayBuffer

/**
 * Defines the 'keywords' in our Scala DSL
 *
 *
 */
trait DSL {

  var operations = ArrayBuffer.empty[Any]

  def add(step: Any) = {
    operations += (step)
    this
  }: DSL

  def toc(uri: String) = add("to Camel:" + uri)

  def fromc(uri: String) = add("from Camel :" + uri)

  def to(uri: String) = add("to Ibex:" + uri)

  def from(uri: String) = add("from Ibex :" + uri)

  def process(processor: IbexProcessor) = add(processor.asInstanceOf[IbexProcessor])

  def realType[W](holder: TypeHolder[W]) = holder.realType

  def stepsInRoute {
    for (elem <- operations) {
      println(elem.getClass + ":" + elem)
    }
  }

  /** def aggregate(expression: Exchange => Any, strategy: AggregationStrategy): SAggregateDefinition

  def as[Target](toType: Class[Target]): DSL

  def attempt: STryDefinition

  def bean(bean: Any): DSL

  def choice: SChoiceDefinition

  def delay(delay: Period): SDelayDefinition

  def dynamicRouter(expression: Exchange => Any): DSL

  def enrich(uri: String, strategy: AggregationStrategy): DSL

  def filter(predicate: Exchange => Any): SFilterDefinition

  def handle[E <: Throwable](block: => Unit)(implicit manifest: Manifest[E]): SOnExceptionDefinition[E]

  def id(id: String): DSL

  def idempotentConsumer(expression: Exchange => Any): SIdempotentConsumerDefinition

  def inOnly(): DSL with Block

  def inOut(): DSL with Block

  def loadbalance: SLoadBalanceDefinition

  def log(message: String): DSL

  def log(level: LoggingLevel, message: String): DSL

  def log(level: LoggingLevel, logName: String, message: String): DSL

  def log(level: LoggingLevel, logName: String, marker: String, message: String): DSL

  def loop(expression: Exchange => Any): SLoopDefinition

  def marshal(format: DataFormatDefinition): DSL

  def multicast: SMulticastDefinition

  def onCompletion: SOnCompletionDefinition

  def onCompletion(predicate: Exchange => Boolean): SOnCompletionDefinition

  def onCompletion(config: Config[SOnCompletionDefinition]): SOnCompletionDefinition

  def otherwise: DSL with Block

  def pipeline: SPipelineDefinition

  def policy(policy: Policy): DSL

  def pollEnrich(uri: String, strategy: AggregationStrategy = null, timeout: Long = 0): DSL

  def process(function: Exchange => Unit): DSL

  def process(processor: Processor): DSL

  def recipients(expression: Exchange => Any): DSL

  def resequence(expression: Exchange => Any): SResequenceDefinition

  def rollback: DSL

  def routingSlip(header: String): DSL

  def routingSlip(header: String, separator: String): DSL

  def routingSlip(expression: Exchange => Any): DSL

  def setBody(expression: Exchange => Any): DSL

  def setFaultBody(expression: Exchange => Any): DSL

  def setHeader(header: String, expression: Exchange => Any): DSL

  def sort[T](expression: Exchange => Any, comparator: Comparator[T] = null): DSL

  def split(expression: Exchange => Any): SSplitDefinition

  def stop: DSL

  def threads: SThreadsDefinition

  def throttle(frequency: Frequency): SThrottleDefinition

  def throwException(exception: Exception): DSL

  def to(uris: String*): DSL

  def transacted: DSL

  def transacted(ref: String): DSL

  def transform(expression: Exchange => Any): DSL

  def unmarshal(format: DataFormatDefinition): DSL

  def validate(expression: Exchange => Any): DSL

  def when(filter: Exchange => Any): DSL with Block

  def wireTap(uri: String): DSL

  def wireTap(uri: String, expression: Exchange => Any): DSL

  def -->(uris: String*): DSL */
  override def toString = super.toString
}

