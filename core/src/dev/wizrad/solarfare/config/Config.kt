package dev.wizrad.solarfare.config

import com.badlogic.gdx.Gdx
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okio.Okio
import java.io.InputStream

class Config {
  // MARK: Properties
  lateinit var seed: Seed
  lateinit var space: Space
  lateinit var solarSystem: SolarSystem
  lateinit var star: Spheroid
  lateinit var planet: Spheroid
  lateinit var keys: Map<Key, String>
  lateinit var camera: Camera
  lateinit var ship: Ship

  // MARK: De/serialization
  fun toJson(): String {
    return adapter().toJson(this)
  }

  companion object {
    fun load(): Config {
      return load(Gdx.files.internal("config.json").read())
    }

    fun load(stream: InputStream): Config {
      val config = adapter().fromJson(Okio.buffer(Okio.source(stream)))
      stream.close()
      return config
    }

    private fun adapter(): JsonAdapter<Config> {
       return Moshi.Builder()
         .add(Key.Adapter())
         .build()
         .adapter(Config::class.java)
    }
  }
}
