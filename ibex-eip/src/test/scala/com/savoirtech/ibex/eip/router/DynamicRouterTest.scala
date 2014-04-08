package com.savoirtech.ibex.eip.router

import org.junit.Test
import com.savoirtech.ibex.api.Message
import org.mockito.Mockito._
import com.savoirtech.ibex.eip.test.EipTest

class DynamicRouterTest extends EipTest {

  @Test
  def testDynamicRouting() {
    val router = new DynamicRouter((msg: Message) => "foo")
    val msg = mock[Message]
    router.execute(msg, context)
    verify(dispatcher).send(msg, "foo")
  }
}
