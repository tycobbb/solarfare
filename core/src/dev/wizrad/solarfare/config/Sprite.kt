package dev.wizrad.solarfare.config

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import dev.wizrad.solarfare.extensions.rand
import dev.wizrad.solarfare.extensions.upto

@JsonObject
class Sprite {
  @JsonField lateinit var name: String
  @JsonField var variants: Int = 0

  //
  // Sampling
  fun sample(): String {
    return name + rand().upto(variants)
  }
}