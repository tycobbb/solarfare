package dev.wizrad.solarfare.support

import com.badlogic.gdx.Gdx

enum class Tag(val key: String) {
  GENERAL("DEF"),
  GENERATION("GEN"),
}

fun error(tag: Tag, message: String) {
  Gdx.app.error(tag.key, message)
}

fun info(tag: Tag, message: String) {
  Gdx.app.log(tag.key, message)
}

fun debug(tag: Tag, message: String) {
  Gdx.app.debug(tag.key, message)
}
