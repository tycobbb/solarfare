package dev.wizrad.solarfare.generation.core

import java.util.HashMap

data class Identifier(
  val tag: String,
  val value: Int) {

  override fun toString(): String {
    return "$tag.$value"
  }

  companion object {
    private var history = HashMap<String, Int>()

    fun next(tag: String): Identifier {
      var value = (history[tag] ?: 0) + 1
      history[tag] = value
      return Identifier(tag, value)
    }
  }
}