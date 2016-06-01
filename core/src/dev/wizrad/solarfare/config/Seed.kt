package dev.wizrad.solarfare.config

class Seed {
  var value: Long = 0
  var isEnabled: Boolean = false

  fun get(): Long? {
    return if(isEnabled) value else null
  }
}