package dev.wizrad.solarfare.game.world.support

import com.badlogic.gdx.physics.box2d.Body
import dev.wizrad.solarfare.support.*

// MARK: Thrust
fun Body.thrust(magnitude: Float, wake: Boolean = true) {
  applyForceToCenter(
    cosf(angle) * magnitude, // x
    sinf(angle) * magnitude, // y
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

// MARK: Orbit
fun Body.beginOrbiting(orbited: Body) {
  // calculate the distance vector
  val delta = orbited.position.cpy().sub(position)

  // calculate speed using simplified equation when orbited body's mass far exceeds
  // the orbiting body's mass: v = √(GM / r)
  val speed = sqrtf(Physics.GRAV_CONSTANT * orbited.mass / delta.len())
  // velocity is perpendicular to the distance vector
  val angle = delta.angleRad() + Maths.F_PI_2

  linearVelocity = Vector.polar(speed, angle)
}

fun Body.gravitateTowards(other: Body) {
  // calculate the distance vector
  val delta     = other.position.cpy().sub(position)
  val distance2 = delta.len2()

  // magnitude calculation fails when distance is zero
  if(distance2 != 0.0f) {
    // calculate using universal law of gravitation: F = G * m1 * m2 / r ^ 2
    val magnitude = Physics.GRAV_CONSTANT * mass * other.mass / distance2
    val gravity   = Vector.polar(magnitude, delta.angleRad())

    applyForceToCenter(gravity, true)
  }
}
