package dev.wizrad.solarfare.config

class Factor {
  /** @property range The range this factor generates over */
  lateinit var range: Range
  /** @property raw The scaling ratio fro the factor's raw value */
  var raw: Double = 1.0
  /** @property available The scaling ratio for the factor's available cost */
  var available: Double = 0.0
  /** @property independent The scaling ratio for the factor's independent cost */
  var independent: Double = 0.0

  //
  // Sampling
  fun sample(): Double {
    return range.sample() * raw
  }

  //
  // Cost
  /** Translates the given raw value into an available cost */
  fun availableCost(value: Double): Int {
    return (available * value / raw).toInt()
  }

  /** Translates the given raw value into an independent cost */
  fun independentCost(value: Double): Int {
    return (independent * value / raw).toInt()
  }
}
