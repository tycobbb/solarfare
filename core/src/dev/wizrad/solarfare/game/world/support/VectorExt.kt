package dev.wizrad.solarfare.game.world.support

import com.badlogic.gdx.math.Vector2
import dev.wizrad.solarfare.support.cos
import dev.wizrad.solarfare.support.sin

class Vector {
  companion object {
    fun polar(magnitude: Float, angle: Float): Vector2 {
      return Vector2(
        cos(angle) * magnitude,
        sin(angle) * magnitude
      )
    }
  }
}
