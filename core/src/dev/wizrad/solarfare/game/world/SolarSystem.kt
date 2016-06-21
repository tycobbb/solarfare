package dev.wizrad.solarfare.game.world

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import dev.wizrad.solarfare.game.core.Entity
import dev.wizrad.solarfare.game.world.core.NodeEntity
import dev.wizrad.solarfare.game.world.support.EntitySequence
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

  override fun children(sequence: EntitySequence): EntitySequence {
    return sequence.first(star).then(planets)
  }

  override fun defineBody(node: SolarSystemNode): BodyDef {
    val body = super.defineBody(node)
    body.position.set(transform(node.center))
    return body
  }

  // MARK: Debugging
  override fun toString(): String {
    return "[system center=$center]"
  }

  val debugColor = nextColor()

  companion object {
    private var index  = 0
    private val colors = arrayOf(
      Color.RED,  Color.BLUE,   Color.CYAN,   Color.MAGENTA, Color.PURPLE,
      Color.PINK, Color.MAROON, Color.ORANGE, Color.CORAL,   Color.TAN)

    private fun nextColor(): Color {
      val color = colors[index]
      index = (index + 1) % colors.size
      return color
    }
  }
}
