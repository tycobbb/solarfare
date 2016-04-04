package dev.wizrad.solarfare.config

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import dev.wizrad.solarfare.extensions.between
import dev.wizrad.solarfare.extensions.rand

@JsonObject
class Range {
  @JsonField var min: Double = 0.0
  @JsonField var max: Double = 0.0

  //
  // Sampling
  fun sample(): Double {
    return rand().between(min, max)
  }
}
