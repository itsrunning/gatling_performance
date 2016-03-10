package tests

import core.Configuration
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scenarios._
import utils.CommonUtils

import scala.concurrent.duration._

class SampleTest extends Simulation {

  val scenario = new Scenario("sample_csv_file.csv")

  val hourlyLoad = 50

  val httpConf = http
    .baseURL(Configuration.baseUrl)
    .warmUp(Configuration.warmUpUrl)

  setUp(
    scenario.Scenario.scn.inject(rampUsers(CommonUtils.adaptiveLoadFor(hourlyLoad)) over (Configuration.rampUpTime minutes))
  ).protocols(httpConf).assertions(
    global.responseTime.percentile1.lessThan(3000),
    global.failedRequests.percent.is(0))

}
