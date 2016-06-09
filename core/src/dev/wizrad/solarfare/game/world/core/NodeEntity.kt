package dev.wizrad.solarfare.game.world.core

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
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
  protected val body: Body
  private var minimapNode: MinimapNode? = null
  override val center: Vector2 get() = body.position

  // MARK: Lifecycle
  init {
    body = world.createBody(defineBody(node))
    createFixtures(node)
  }

  override fun destroy() {
    // stop tracking this node on the map when it's destroyed (for now)
    minimapNode?.destroy()
    minimapNode = null

    super.destroy()
  }

  // MARK: Physics
  open protected fun defineBody(node: N): BodyDef {
    return BodyDef()
  }

  open protected fun createFixtures(node: N) {
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
