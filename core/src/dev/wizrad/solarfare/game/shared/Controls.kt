package dev.wizrad.solarfare.game.shared

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.config.Key
import javax.inject.Inject

class Controls @Inject constructor(
  config: Config) {

  // MARK: Properties
  private val keys = mapKeys(config.keys)

  // MARK: Evaluation
  fun pressed(key: Key): Boolean {
    val code   = checkNotNull(keys[key]) { "$this no code for $key" }
    val result = Gdx.input.isKeyPressed(code)
    return result
  }

  // MARK: Mapping
  private fun mapKeys(keys: Map<Key, String>): Map<Key, Int> {
    return keys.mapValues { entry ->
      val code = Input.Keys.valueOf(entry.value)
      check(code != -1) { "$this no mapping for $entry" }
      code
    }
  }

  // MARK: Debugging
  override fun toString(): String {
    return "[Controls]"
  }
}
