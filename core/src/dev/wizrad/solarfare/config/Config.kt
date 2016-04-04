package dev.wizrad.solarfare.config

import com.badlogic.gdx.Gdx
import java.io.InputStream
import com.bluelinelabs.logansquare.LoganSquare
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
class Config {
  //
  // Fields
  @JsonField lateinit var seed: Seed
  @JsonField lateinit var space: Space
  @JsonField lateinit var solarSystem: SolarSystem
  @JsonField lateinit var star: Spheroid
  @JsonField lateinit var planet: Spheroid
  @JsonField lateinit var keys: Keys
  @JsonField lateinit var camera: Camera

  //
  // Loading
  companion object {
    fun load(): Config {
      return load(Gdx.files.internal("config.json").read())
    }

    fun load(stream: InputStream): Config {
      val config = LoganSquare.parse(stream, Config::class.java)
      stream.close()
      return config
    }
  }
}
