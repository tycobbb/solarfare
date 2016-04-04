package dev.wizrad.solarfare.config

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
class Keys {
  @JsonField lateinit var thrust:     String
  @JsonField lateinit var leftBank:   String
  @JsonField lateinit var rightBank:  String
  @JsonField lateinit var reverse:    String
  @JsonField lateinit var mouseTouch: String
}
