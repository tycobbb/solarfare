package dev.wizrad.solarfare.game.world

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import dev.wizrad.solarfare.game.core.Entity
import dev.wizrad.solarfare.game.core.update
import dev.wizrad.solarfare.game.world.core.NodeEntity
import dev.wizrad.solarfare.generation.SolarSystemNode
import dev.wizrad.solarfare.support.Tag
import dev.wizrad.solarfare.support.debug

class SolarSystem(
  node:   SolarSystemNode,
  parent: Entity,
  world:  World): NodeEntity<SolarSystemNode>(node, world, parent) {

  // MARK: Children
  lateinit var star:    Star
  lateinit var planets: List<Planet>

  // MARK: Lifecycle
  init {
    debug(Tag.World, "$this created")
  }

  override fun defineBody(node: SolarSystemNode): BodyDef {
    val body = super.defineBody(node)
    body.position.set(transform(node.center))
    return body
  }

  override fun update(delta: Float) {
    super.update(delta)
    star.update(delta)
    planets.update(delta)
  }

  // MARK: Debugging
  override fun toString(): String {
    return "[system center=$center]"
  }
}
