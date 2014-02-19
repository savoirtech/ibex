package com.savoirtech.ibex.api

import com.savoirtech.ibex.IbexExchange

trait  IbexProcessor {


  def process(exchange: IbexExchange)


}
