package dev.wizrad.solarfare.extensions

import java.util.Random

fun rand(): Random {
  return Random()
}

fun Random.upto(max: Int): Int {
  return nextInt(max)
}

fun Random.upto(max: Double): Double {
  return nextDouble() * max
}

fun Random.between(min: Int, max: Int): Int {
  return min + upto(max - min)
}

fun Random.between(min: Double, max: Double): Double {
  return min + upto(max - min)
}
