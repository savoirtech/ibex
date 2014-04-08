package com.savoirtech.ibex.eip.translator

import com.savoirtech.ibex.eip.test.EipTest
import org.junit.Test
import com.savoirtech.ibex.api.Message
import org.mockito.Mockito._

class TranslatorTest extends EipTest {
  @Test
  def testTranslator(): Unit = {
    val output = mock[Message]
    val input = mock[Message]

    val splitter = new Translator((msg: Message) => output)
    splitter.execute(input, context)
    verify(dispatcher).send(output)

  }
}
