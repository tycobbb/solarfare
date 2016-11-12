package dev.wizrad.solarfare.game.world

import com.badlogic.gdx.physics.box2d.World
import dev.wizrad.solarfare.game.core.Entity
import dev.wizrad.solarfare.game.ui.minimap.Minimap
import dev.wizrad.solarfare.generation.StarNode

class Star(
  node:    StarNode,
  parent:  Entity,
  world:   World,
  minimap: Minimap): Spheroid<StarNode>(node, parent, world) {

  init {
    // minimap
    trackOn(minimap)
  }
}
