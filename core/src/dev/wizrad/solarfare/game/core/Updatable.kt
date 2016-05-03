package dev.wizrad.solarfare.game.core

interface Updatable {
  fun update(delta: Float)
}

fun <E: Updatable> Iterable<E>.update(delta: Float) {
  for(element in this) {
    element.update(delta)
  }
}
