package dev.wizrad.solarfare.support.extensions

import dev.wizrad.solarfare.support.cache
import java.util.*

val rand: () -> Random = cache { Random() }

fun Random.upto(max: Int): Int = nextInt(max)
fun Random.upto(max: Double): Double = nextDouble() * max

fun Random.between(min: Int, max: Int): Int = min + upto(max - min)
fun Random.between(range: ClosedRange<Int>): Int = between(range.start, range.endInclusive)

fun Random.between(min: Double, max: Double): Double = min + upto(max - min)
fun Random.between(range: ClosedRange<Double>): Double = between(range.start, range.endInclusive)

fun Random.stride(min: Int, length: Int): Int = min + upto(length)
fun Random.stride(min: Double, length: Double): Double = min + upto(length)
