package example

import akka.actor.{Actor, ActorRef, Props}

object Client {
  def props(storage: ActorRef) = Props(new Client(storage))

  case class Connected(outgoing: ActorRef)

  object Get
}

/**
  * It's a mediator between the storage and the network actor.
  *
  * The first message that it should get is the Client.Connected message. After that the client starts to mediate
  * between the storage and the "outgoing" network actor. It just retransmits messages from the storage to the network
  * actor and back.
  *
  * @param storage the storage actor
  */
class Client(storage: ActorRef) extends Actor {

  import Client._

  override def receive: Receive = {
    case Connected(outgoing) =>
      context.become(connected(outgoing))
  }

  private def connected(outgoing: ActorRef): Receive = {
    def calcReceiver(sender: ActorRef) = {
      if (sender == storage) outgoing
      else storage
    }

    {
      case Get =>
        calcReceiver(sender()) ! Get
    }
  }
}
