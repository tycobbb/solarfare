package dev.wizrad.solarfare.game.renderer

import dev.wizrad.solarfare.game.world.World
import javax.inject.Inject

class Renderer @Inject constructor(
  private val world:  World,
  private val camera: Camera) {

  fun render() {

  }
}
