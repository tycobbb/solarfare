package dev.wizrad.solarfare.config

import dev.wizrad.solarfare.support.extensions.rand

class Sprite {
  var name: String  = ""
  var variants: Int = 0

  // MARK: Sampling
  fun sample(): String {
    return name + rand.upto(variants)
  }
}