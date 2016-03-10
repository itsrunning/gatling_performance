package utils

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

import core.Configuration

object CommonUtils {

  def timeStamp(): String = {
    new Date().toString
  }

  def currentDate(): String = {
    adjustedDateFromNow(0)
  }

  def futureDate(delta: Int = 5): String = {
    adjustedDateFromNow(delta)
  }

  def pastDate(delta: Int = -5): String = {
    adjustedDateFromNow(delta)
  }

  def adjustedDateFromNow(delta: Int): String = {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, delta)
    new SimpleDateFormat("dd MMM yyyy").format(calendar.getTime)
  }

  def hasWonTheToss: Boolean = {
    Math.random() <= 0.5
  }

  def adaptiveLoadFor(hourlyLoad: Int): Int = {
    var loadFactor = 1.0
    if (Configuration.rampUpTime > 60) {
      loadFactor = Configuration.rampUpTime / 60.0
    }
    (hourlyLoad * loadFactor * Configuration.loadMultiplier).toInt
  }
}
