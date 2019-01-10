package io.gatling.amqp.config

import akka.actor._
import io.gatling.commons.util.DefaultClock
import io.gatling.core.controller.throttle.Throttler
import io.gatling.core.stats.{DataWritersStatsEngine, StatsEngine}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * run preparings in console
 */
trait AmqpRunner { this: AmqpProtocol =>
  def run(): Unit = {
    val system = ActorSystem("AmqpRunner")
    try {
      val statsEngine: StatsEngine = new DataWritersStatsEngine(Seq[ActorRef](), system, new DefaultClock())
      val throttler  : Throttler   = null  // just use manage Actor
      //warmUp(system, statsEngine, throttler)
    } catch {
      case e: Throwable =>
        // maybe failed to declare queue like inequivalent args
        if (e.getCause() != null)
          logger.error(s"failed: ${e.getCause}")
        else
          logger.error(s"failed: $e", e)
    } finally {
      Await.result(system.terminate(), Duration.Inf)
     }
  }
}
