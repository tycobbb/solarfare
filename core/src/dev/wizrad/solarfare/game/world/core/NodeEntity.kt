package dev.wizrad.solarfare.game.world.core

import com.badlogic.gdx.physics.box2d.World
import dev.wizrad.solarfare.game.core.Entity
import dev.wizrad.solarfare.game.ui.Mappable
import dev.wizrad.solarfare.game.ui.Minimap
import dev.wizrad.solarfare.game.ui.MinimapNode
import dev.wizrad.solarfare.generation.core.Node

abstract class NodeEntity<N: Node>(
  node:    N,
  world:   World,
  parent:  Entity? = null): Entity(parent), Mappable {

  // MARK: Properties
  private var minimapNode: MinimapNode? = null

  // MARK: Lifecycle
  override fun destroy() {
    // stop tracking this node on the map when it's destroyed (for now)
    minimapNode?.destroy()
    minimapNode = null

    super.destroy()
  }

  // MARK: Minimap
  protected fun trackOn(minimap: Minimap) {
    val node = minimap.track(this)
    configure(node)
    minimapNode = node
  }

  open protected fun configure(node: MinimapNode) {
  }
}
