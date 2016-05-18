package dev.wizrad.solarfare.config

class Factor {
  /** @property range The range this factor generates over */
  lateinit var range: Range
  /** @property raw The scaling ratio fro the factor's raw value */
  var raw: Double = 1.0

  // MARK: Sampling
  fun sample(): Double {
    return range.sample() * raw
  }
}
