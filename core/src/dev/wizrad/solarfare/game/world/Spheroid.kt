package dev.wizrad.solarfare.game.world

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import dev.wizrad.solarfare.game.core.Entity
import dev.wizrad.solarfare.game.world.core.NodeEntity
import dev.wizrad.solarfare.generation.SpheroidNode
import dev.wizrad.solarfare.support.Tag
import dev.wizrad.solarfare.support.debug
import dev.wizrad.solarfare.support.fmt

abstract class Spheroid<N: SpheroidNode>(
  node:   N,
  parent: Entity,
  world:  World): NodeEntity<N>(node, world, parent) {

  // MARK: Geometry
  var radius = node.radius.toFloat()

  // MARK: Lifecycle
  init {
    debug(Tag.World, "$this created")
  }

  override fun defineBody(node: N): BodyDef {
    val body = super.defineBody(node)
    body.position.set(transform(node.center))
    return body
  }

  // MARK: Debugging
  override fun toString(): String {
    return "[spheroid center=$center rad=${radius.fmt()}]"
  }
}