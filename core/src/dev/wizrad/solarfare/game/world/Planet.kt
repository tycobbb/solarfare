package dev.wizrad.solarfare.game.world

import com.badlogic.gdx.physics.box2d.World
import dev.wizrad.solarfare.game.core.Entity
import dev.wizrad.solarfare.game.ui.Minimap
import dev.wizrad.solarfare.generation.PlanetNode

class Planet(
  node:    PlanetNode,
  parent:  Entity,
  world:   World,
  minimap: Minimap): Spheroid<PlanetNode>(node, parent, world) {

  init {
    // minimap
    trackOn(minimap)
  }
}
