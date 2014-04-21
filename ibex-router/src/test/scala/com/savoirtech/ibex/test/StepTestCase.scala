/*
 * Copyright (C) 2014 Savoir Technologies, Inc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.savoirtech.ibex.test

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestKit}
import com.savoirtech.ibex.api.{Path, Traversal}
import org.scalatest._
import scala.concurrent.Future
import akka.dispatch.Futures
import com.savoirtech.ibex.api.Message

abstract class StepTestCase extends TestKit(ActorSystem("IbexTesting")) with ImplicitSender with FlatSpecLike with Matchers with BeforeAndAfterAll {

  override def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }

  def newTraversal(message: Message) = TestTraversal(message)

  def newPath() = new TestPath()

  case class TestTraversal(message: Message) extends Traversal {
    override def inject(path: Path): Unit = testActor ! new PathInjection(path, message)

    override def inject(path: Path, message: Message): Unit = testActor ! new PathInjection(path, message)

    override def inject(uri: String): Unit = testActor ! new UriInjection(uri, message)

    override def inject(uri: String, message: Message): Unit = testActor ! new UriInjection(uri, message)

    override def proceed(message: Message): Unit = testActor ! new Proceed(message)
  }

  class TestPath extends Path {
    override def request(message: Message): Future[Message] = {
      testActor ! Request(this, message)
      Futures.successful(message)
    }

    override def send(message: Message): Unit = testActor ! Send(this, message)
  }

  case class PathInjection(path: Path, message: Message)

  case class UriInjection(uri: String, message: Message)

  case class Proceed(message: Message)

  case class Send(path: Path, message: Message)

  case class Request(path: Path, message: Message)

}

