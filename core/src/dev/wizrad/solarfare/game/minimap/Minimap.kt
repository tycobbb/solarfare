package dev.wizrad.solarfare.game.minimap

import dev.wizrad.solarfare.game.core.Entity
import dev.wizrad.solarfare.game.core.update

class Minimap: Entity(null) {
  // MARK: Properties
  private val nodes: MutableMap<Int, Node> = mutableMapOf()

  // MARK: Tracking
  fun track(mappable: Mappable): Node {
    val node = Node(mappable, this)
    nodes[node.id] = node
    return node
  }

  private fun stopTracking(node: Node) {
    nodes.remove(node.id)
  }

  // MARK: Updatable
  override fun update(delta: Float) {
    nodes.values.update(delta)
  }

  // MARK: Node
  class Node(
    private val mappable: Mappable,
    parent: Entity): Entity(parent) {

    // MARK: Properties
    val id: Int = Node.nextId()
    private val minimap: Minimap? get() = parent as? Minimap

    // MARK: Lifecycle
    override fun update(delta: Float) {
      super.update(delta)
    }

    override fun destroy() {
      minimap?.stopTracking(this)
      super.destroy()
    }

    companion object {
      private var id = 0
      private fun nextId(): Int {
        return id++
      }
    }
  }
}
