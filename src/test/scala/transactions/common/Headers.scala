package transactions.common

object Headers {

  val jsonHeaderWithToken = Map(
    "Accept" -> "application/json, text/plain, */*",
    "Content-Type" -> "application/json;charset=utf-8",
    "X-CSRF-TOKEN" -> "${valid_token}",
    "X-HTTP-Method-Override" -> "PUT",
    "X-Requested-With" -> "XMLHttpRequest"
  )

  val fileUploadHeaderWithToken = Map(
    "Accept" -> "application/json, text/plain, */*",
    "Content-Type" -> "multipart/form-data",
    "X-CSRF-TOKEN" -> "${valid_token}",
    "X-Requested-With" -> "XMLHttpRequest"
  )

  val pdfDownloadHeader = Map(
    "Accept" -> "application/pdf, text/plain, application/x-pdf, */*"
  )

  val csvDownloadHeader = Map(
    "Accept" -> "text/csv, text/plain, */*"
  )
}
