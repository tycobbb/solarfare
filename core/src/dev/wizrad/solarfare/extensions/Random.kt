package dev.wizrad.solarfare.extensions

import java.util.Random

fun rand(): Random {
  return Random()
}

fun Random.upto(max: Int): Int {
  return nextInt(max)
}
