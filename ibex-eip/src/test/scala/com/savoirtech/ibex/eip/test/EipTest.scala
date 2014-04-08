package com.savoirtech.ibex.eip.test

import org.scalatest.mock.MockitoSugar
import com.savoirtech.ibex.api.{MessageDispatcher, MessagingContext}
import org.mockito.Mockito._

abstract class EipTest extends AnyRef with MockitoSugar {

  val context = mock[MessagingContext]
  val dispatcher = mock[MessageDispatcher]
  when(context.dispatcher()).thenReturn(dispatcher)

}
