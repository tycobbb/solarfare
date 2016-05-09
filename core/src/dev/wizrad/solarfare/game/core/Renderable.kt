package dev.wizrad.solarfare.game.core

interface Renderable: Updatable {
  fun resize(width: Int, height: Int)
}
