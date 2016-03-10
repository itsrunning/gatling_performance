package core

object Configuration {

  val baseUrl = System.getenv("BASE_URL")

  val rampUpTime = System.getenv("RAMP_UP_TIME_IN_MINUTES").toInt

  val loadMultiplier = System.getenv("LOAD_MULTIPLIER").toFloat

  val warmUpUrl = s"$baseUrl/ping"
}
