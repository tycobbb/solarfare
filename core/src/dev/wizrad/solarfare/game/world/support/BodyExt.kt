package dev.wizrad.solarfare.game.world.support

import com.badlogic.gdx.physics.box2d.Body
import dev.wizrad.solarfare.support.Maths

// MARK: Thrust
fun Body.thrust(magnitude: Float, wake: Boolean = true) {
  applyForceToCenter(
    Maths.cosf(angle) * magnitude, // x
    Maths.sinf(angle) * magnitude, // y
    wake)
}

// MARK: Rotation
fun Body.rotate(radians: Float) {
  val angle = (angle + radians) % Maths.F_2PI
  setTransform(position, angle)
}

// MARK: Velocity
fun Body.limitVelocity(minimum: Float = 0.0f, maximum: Float = Float.MAX_VALUE) {
  val magnitude = linearVelocity.len()

  // otherwise apply the minimum velocity
  if(magnitude < minimum) {
    linearVelocity = Vector.polar(minimum, angle)
  }
  // otherwise apply the maximum velocity
  else if(magnitude > maximum) {
    linearVelocity = Vector.polar(maximum, angle)
  }
}
