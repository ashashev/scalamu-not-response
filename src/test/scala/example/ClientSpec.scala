package example

import akka.actor.ActorSystem
import akka.testkit.{TestKit, TestProbe}
import org.scalatest.FlatSpecLike

import scala.concurrent.duration._
import scala.language.postfixOps

import Client._

class ClientSpec(_system: ActorSystem)
  extends TestKit(_system)
  with FlatSpecLike {

  def this() = this(ActorSystem("ClientSpec"))

  "A client actor" should "retransmits all expected messages from the outgoing to the storage" in {
    val storage = TestProbe()
    val outgoing = TestProbe()
    val client = system.actorOf(Client.props(storage.ref))

    client ! Client.Connected(outgoing.ref)

    outgoing send(client, Get)
    storage expectMsg(500 millis, Get)
  }
}
