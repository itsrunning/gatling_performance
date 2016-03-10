package transactions.common

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

object LoginTransactions {

  val loadLoginPage = group("[Common] Group: Load login page") {
    exec(http("Load login page")
      .get("/login")
      .check(css("meta[name=csrf-token]", "content").saveAs("token_before_login"))
    )
  }

  val submitCredentials = group("[Common] Group: Submit the login credentials") {
    exec(http("Submit the login credentials")
      .post("/login")
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "${token_before_login}")
      .formParam("user[username]", "${username}")
      .formParam("user[password]", "${password}")
      .check(css("meta[name=csrf-token]", "content").saveAs("csrf_token"))
    )
  }

  val logout = group("[Common] Group: Logout") {
    exec(http("Logout")
      .get("/logout")
      .queryParam("authenticity_token", "${csrf_token}")
    )
  }

  def login() = {
    tryMax(5) {
      exec(loadLoginPage)
        .exitHereIfFailed
        .pause(5 seconds, 10 seconds)
        .exec(submitCredentials)
        .exitHereIfFailed
    }
  }
}
