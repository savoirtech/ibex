package com.savoirtech.ibex.eip.splitter

import com.savoirtech.ibex.eip.test.EipTest
import org.junit.Test
import com.savoirtech.ibex.api.Message
import org.mockito.Mockito._

class SplitterTest extends EipTest {

  @Test
  def testSplitting(): Unit = {
    val split1 = mock[Message]
    val split2 = mock[Message]
    val input = mock[Message]

    val splitter = new Splitter((msg: Message) => List(split1, split2))
    splitter.execute(input, context)
    verify(dispatcher).send(split1)
    verify(dispatcher).send(split2)
  }
}
