package io.gatling.amqp.config

import io.gatling.core.protocol.ProtocolComponents
import io.gatling.core.session.Session

/**
  * Created by Ľubomír Varga on 11.5.2016.
  */
case class AmqpComponents(amqpProtocol: AmqpProtocol) extends ProtocolComponents {
  override def onStart: (Session) => Session = x => x

  override def onExit: (Session) => Unit = x => None
}
