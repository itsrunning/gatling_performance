package transactions.common

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import utils.CommonUtils

object FileUploader {

  def initialize() = {
    exec(session => session
      .set("file_1_id", "")
      .set("file_2_id", "")
    )
  }

  def fileToUpload() = {
    val fileName = if (CommonUtils.hasWonTheToss) "small.pdf" else "large.pdf"
    stringToExpression(fileName)
  }

  def upload(sessionVarName: String, purpose: String) = {
    val description = s"[File Upload] Upload file for $purpose"
    val file = fileToUpload()
    exec(http(stringToExpression(description))
      .post("/upload")
      .headers(Headers.fileUploadHeaderWithToken)
      .bodyPart(StringBodyPart("purpose", purpose))
      .bodyPart(RawFileBodyPart("file", file).contentType("application/pdf").fileName(file))
      .check(css(". uploaded_document", "uuid").saveAs(sessionVarName))
    )
    .exitHereIfFailed
  }

  val uploadFile1 = upload("file_1_id", "purpose_1")

  val uploadFile2 = upload("file_2_id", "purpose_1")
}
