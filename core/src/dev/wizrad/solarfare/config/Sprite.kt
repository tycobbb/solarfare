package dev.wizrad.solarfare.config

import dev.wizrad.solarfare.support.extensions.rand
import dev.wizrad.solarfare.support.extensions.upto

class Sprite {
  lateinit var name: String
  var variants: Int = 0

  //
  // Sampling
  fun sample(): String {
    return name + rand().upto(variants)
  }
}