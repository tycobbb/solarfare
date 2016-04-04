package dev.wizrad.solarfare.config

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
class Camera {
  enum class Type { Free, Pin, Track }

  @JsonField var height: Double = 0.0
}

