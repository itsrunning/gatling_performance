package transactions.app

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import transactions.common.Headers
import utils.CommonUtils

object ApplicationTransactions {

  val viewListOfEntries = exec(http("[Home Page] View all entries")
    .get("/dashboard")
  )

  val proceedToNewEntryForm = exec(http("[Home Page] Proceed to the new entry form")
    .get("/transaction/new")
  )

  val addNewEntry = group("[Entry Page] Group: Create a new entry") {
    exec(http("Create a new entry")
      .post("/transaction/new")
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "${csrf_token}")
      .formParam("trans_date", CommonUtils.currentDate())
      .formParam("trans_description", "${trans_description}")
      .formParam("trans_category", "${trans_category}")
      .formParam("trans_value", "${trans_value}")
      .check(currentLocationRegex("/transaction/(.*)").saveAs("transaction_id"))
    )
  }

  val proceedToEntryUpdateForm = exec(http("[Home Page] Proceed to the entry update form")
    .get("/transaction/${transaction_id}/edit")
  )

  val updateEntry = group("[Entry Page] Group: Update entry") {
    exec(http("Update entry")
      .post("/transaction/${transaction_id}/edit")
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "${csrf_token}")
      .formParam("trans_date", CommonUtils.currentDate())
      .formParam("trans_description", "${new_trans_description}")
      .formParam("trans_category", "${new_trans_category}")
      .formParam("trans_value", "${new_trans_value}")
    )
  }

  val deleteEntry = group("[Home Page] Group: Delete entry") {
    exec(http("Delete entry")
      .delete("/transaction/${transaction_id}/delete")
    )
  }

  val postJson = exec(http("Sample - Post Json")
    .post("/post_url")
    .headers(Headers.jsonHeaderWithToken)
    .body(ELFileBody("sample_json_body.txt")).asJSON
    .check(regex("/pattern/(.*)/some_other_pattern").saveAs("some_identifier"))
    .check(currentLocationRegex("/pattern/(.*)").saveAs("some_identifier_from_url"))
  )

  val pdfDownload = exec(http("Sample - PDF Download")
    .get("/download_url")
    .headers(Headers.pdfDownloadHeader)
  )

  val csvDownload = exec(http("Sample - CSV Download")
    .get("/download_url")
    .headers(Headers.csvDownloadHeader)
  )
}
