package dev.wizrad.solarfare.config

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
class Space {
  /** @property size Factor for one dimension of the world */
  @JsonField lateinit var size: Factor
}
