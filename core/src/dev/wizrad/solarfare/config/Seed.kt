package dev.wizrad.solarfare.config

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
class Seed {
  @JsonField var value: Int = 0
  @JsonField var isEnabled: Boolean = false
}