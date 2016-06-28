package dev.wizrad.solarfare.game.world

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World
import dev.wizrad.solarfare.config.Key
import dev.wizrad.solarfare.game.core.Entity
import dev.wizrad.solarfare.game.renderer.core.CameraTrackable
import dev.wizrad.solarfare.game.shared.Controls
import dev.wizrad.solarfare.game.ui.Minimap
import dev.wizrad.solarfare.game.ui.MinimapNode
import dev.wizrad.solarfare.game.world.core.NodeEntity
import dev.wizrad.solarfare.game.world.support.limitVelocity
import dev.wizrad.solarfare.game.world.support.rotate
import dev.wizrad.solarfare.game.world.support.thrust
import dev.wizrad.solarfare.generation.ShipNode
import dev.wizrad.solarfare.support.Maths

class Ship(
  node:     ShipNode,
  parent:   Entity,
  world:    World,
  minimap:  Minimap,
  controls: Controls): NodeEntity<ShipNode>(node, world, parent), CameraTrackable {

  // MARK: Dependencies
  private val controls = controls

  // MARK: Lifecycle
  init {
    trackOn(minimap)
  }

  override fun defineBody(node: ShipNode): BodyDef {
    val body = super.defineBody(node)
    body.type  = BodyType.DynamicBody
    body.angle = -Maths.F_PI_2
    body.position.set(transform(node.center))
    return body
  }

  override fun createFixtures(node: ShipNode) {
    super.createFixtures(node)

    // define fixtures
    val fixture  = FixtureDef()

    val triangle = PolygonShape()
    triangle.set(floatArrayOf(
       0.0f, -1.0f,
       1.0f,  1.0f,
      -1.0f,  1.0f
    ))

    fixture.shape = triangle

    // add fixtures to body
    body.createFixture(fixture)
    // clean up
    triangle.dispose()
  }

  override fun update(delta: Float) {
    super.update(delta)

    // TODO: config movement based on ship
    if(controls.pressed(Key.RotateLeft)) {
      body.rotate(-Maths.F_PI_2 / 64.0f)
    }

    if(controls.pressed(Key.RotateRight)) {
      body.rotate(Maths.F_PI_2 / 64.0f)
    }

    if(controls.pressed(Key.Thrust)) {
      body.thrust(15.0f)
    }

    if(controls.pressed(Key.Reverse)) {
      body.thrust(-15.0f)
    }
  }

  override fun afterStep(delta: Float) {
    super.afterStep(delta)

    // TODO: config max speed based on ship
    body.limitVelocity(maximum = 80.0f)
  }

  // MARK: Minimap
  override fun configure(node: MinimapNode) {
    super.configure(node)
    node.color = Color.GREEN
  }

  // MARK: CameraTrackable
  override val point: Vector2 get() = center
}
