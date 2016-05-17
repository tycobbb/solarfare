package dev.wizrad.solarfare.support

import com.badlogic.gdx.Gdx

// MARK: Logging
enum class Tag(val key: String) {
  GENERAL("DEF"),
  GENERATION("GEN"),
  CLUSTERING("CLS"),
  WORLD("WRL"),
}

fun error(tag: Tag, message: String) {
  Gdx.app.error("(E) ${tag.key}", message)
}

fun info(tag: Tag, message: String) {
  Gdx.app.log("(I) ${tag.key}", message)
}

fun debug(tag: Tag, message: String) {
  Gdx.app.debug("(D) ${tag.key}", message)
}

// MARK: Formatting
fun Number.fmt(precision: Int = 3): String {
  return String.format("%.${precision}f", this)
}
