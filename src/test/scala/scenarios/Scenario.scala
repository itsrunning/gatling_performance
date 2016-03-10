package scenarios

import io.gatling.core.Predef._
import transactions.app.ApplicationTransactions
import transactions.common.LoginTransactions

import scala.concurrent.duration._

class Scenario(fileName: String) {

  object Scenario {
    val data = csv(fileName)

    val scn = scenario("Workflow")
      .feed(data)
      .exec(LoginTransactions.login())
      .pause(5 seconds, 10 seconds)
      .exec(ApplicationTransactions.viewListOfEntries)
      .pause(20 seconds, 40 seconds)
      .exec(ApplicationTransactions.proceedToNewEntryForm)
      .pause(5 seconds, 8 seconds)
      .exec(ApplicationTransactions.addNewEntry)
      .pause(5 seconds, 8 seconds)
      .exec(ApplicationTransactions.proceedToEntryUpdateForm)
      .pause(5 seconds, 8 seconds)
      .exec(ApplicationTransactions.updateEntry)
      .pause(5 seconds, 8 seconds)
      .exec(ApplicationTransactions.deleteEntry)
      .pause(5 seconds, 8 seconds)
      .exec(LoginTransactions.logout)
  }
}
