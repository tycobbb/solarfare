package dev.wizrad.solarfare.game.world.core

import dev.wizrad.solarfare.game.core.Entity
import dev.wizrad.solarfare.game.ui.Mappable
import dev.wizrad.solarfare.game.ui.Minimap
import dev.wizrad.solarfare.game.ui.MinimapNode
import dev.wizrad.solarfare.generation.core.Node

abstract class NodeEntity<N: Node>(
  node:    N,
  parent:  Entity?  = null,
  minimap: Minimap? = null): Entity(parent), Mappable {

  // MARK: Properties
  private val minimapNode: MinimapNode?

  // MARK: Lifecycle
  init {
    minimapNode = minimap?.track(this)
    if(minimapNode != null) {
      configure(minimapNode)
    }
  }

  open protected fun configure(node: MinimapNode) {
  }

  override fun destroy() {
    // stop tracking this node on the map when it's destroyed (for now)
    minimapNode?.destroy()
    super.destroy()
  }
}
