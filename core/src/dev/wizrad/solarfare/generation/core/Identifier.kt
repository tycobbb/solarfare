package dev.wizrad.solarfare.generation.core

import java.util.*

data class Identifier(
  val tag: String,
  val value: Int) {

  override fun toString(): String {
    return "$tag.$value"
  }

  companion object {
    private var history = HashMap<String, Int>()

    fun next(tag: String): Identifier {
      val value = history[tag] ?: 0
      history[tag] = value + 1
      return Identifier(tag, value)
    }
  }
}