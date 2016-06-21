package dev.wizrad.solarfare.game.world.support

import dev.wizrad.solarfare.game.core.Entity

fun <E: Entity> Array<E>.update(delta: Float) {
  for(entity in this) {
    entity.update(delta)
  }
}

fun <E: Entity> Array<E>.afterStep(delta: Float) {
  for(entity in this) {
    entity.afterStep(delta)
  }
}
