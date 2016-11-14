package dev.wizrad.solarfare.game.world

import dev.wizrad.solarfare.game.core.Entity
import dev.wizrad.solarfare.game.ui.minimap.MinimapNode
import dev.wizrad.solarfare.generation.PlanetNode

class Planet(
  node:    PlanetNode,
  parent:  Entity,
  world:   World): Spheroid<PlanetNode>(node, parent, world) {

  init {
    trackOn(world.minimap)
  }

  // MARK: Minimap
  override fun configure(node: MinimapNode) {
    super.configure(node)

    val color = system?.debugColor
    if(color != null) {
      node.color = color
    }
  }

  // MARK: Debugging
  val system: SolarSystem? get() = parent as? SolarSystem
}
