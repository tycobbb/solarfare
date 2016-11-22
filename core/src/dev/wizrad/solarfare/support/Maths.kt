package dev.wizrad.solarfare.support

import com.badlogic.gdx.math.MathUtils

// MARK: Trigonometry
val cos:    (Float)  -> Float  = MathUtils::cos
val cosd:   (Double) -> Double = Math::cos

val sin:    (Float)  -> Float  = MathUtils::sin
val sind:   (Double) -> Double = Math::sin

val atan2:  (Float,  Float)  -> Float  = MathUtils::atan2
val atan2d: (Double, Double) -> Double = Math::atan2

// MARK: Utilities
val sqrt:  (Float)  -> Float  = { sqrtd(it.toDouble()).toFloat() }
val sqrtd: (Double) -> Double = Math::sqrt

val abs:   (Float)  -> Float  = Math::abs
val absd:  (Double) -> Double = Math::abs

// MARK: Constants
class Maths {
  companion object {
    val D_PI_2 = 1.57079632679489661923132169163975
    val D_2PI  = 6.28318530717958647692528676655901

    val F_PI_2 = D_PI_2.toFloat()
    val F_2PI  = D_2PI.toFloat()

    val RAD2DEG = MathUtils.radDeg
  }
}
