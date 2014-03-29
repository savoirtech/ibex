package com.savoirtech.ibex.test

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestKit}
import org.junit.After

abstract class AkkaTestCase extends TestKit(ActorSystem("IbexTesting")) with ImplicitSender {

  @After
  def shutdownActorSystem(): Unit = {
    TestKit.shutdownActorSystem(system)
  }

}

