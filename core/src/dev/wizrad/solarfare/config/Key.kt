package dev.wizrad.solarfare.config

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

enum class Key {
  Thrust,
  Reverse,
  RotateLeft,
  RotateRight;

  class Adapter {
    @ToJson   fun toJson(key: Key) = key.name.decapitalize()
    @FromJson fun fromJson(code: String) = Key.valueOf(code.capitalize())
  }
}
