package dev.wizrad.solarfare.config

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
class Spheroid {
  @JsonField lateinit var sprite: Sprite
  @JsonField lateinit var mass: Factor
  @JsonField lateinit var radius: Factor
}
