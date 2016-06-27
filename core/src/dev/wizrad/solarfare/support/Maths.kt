package dev.wizrad.solarfare.support

import com.badlogic.gdx.math.MathUtils

// MARK: Trigonometry
val cosd: (Double) -> Double = Math::cos
val sind: (Double) -> Double = Math::sin
val cosf: (Float)  -> Float  = MathUtils::cos
val sinf: (Float)  -> Float  = MathUtils::sin

// MARK: Utilities
val sqrtd: (Double) -> Double = Math::sqrt
val sqrtf: (Float)  -> Float  = { sqrtd(it.toDouble()).toFloat() }

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
