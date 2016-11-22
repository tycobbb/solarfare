package dev.wizrad.solarfare.game.world

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import dev.wizrad.solarfare.config.Key
import dev.wizrad.solarfare.game.components.controls.Touch
import dev.wizrad.solarfare.game.components.projection.Projections
import dev.wizrad.solarfare.game.components.projection.Projections.Companion.normal
import dev.wizrad.solarfare.game.components.projection.projector
import dev.wizrad.solarfare.game.components.route.Route
import dev.wizrad.solarfare.game.core.Entity
import dev.wizrad.solarfare.game.core.Targetable
import dev.wizrad.solarfare.game.ui.minimap.MinimapNode
import dev.wizrad.solarfare.game.world.core.NodeEntity
import dev.wizrad.solarfare.game.world.support.clampSpeed
import dev.wizrad.solarfare.game.world.support.rotate
import dev.wizrad.solarfare.game.world.support.thrust
import dev.wizrad.solarfare.generation.ShipNode
import dev.wizrad.solarfare.support.Maths
import dev.wizrad.solarfare.support.extensions.angleTo

class Ship(
  node:   ShipNode,
  parent: Entity,
  world:  World): NodeEntity<ShipNode>(node, parent, world), Targetable {

  // MARK: Properties
  private var wayfindingRoute: Route? = null
  private var route: List<Vector2>? = null
  private var routeIndex = 0

  // MARK: Lifecycle
  init {
    trackOn(world.minimap)
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

    resolveWayfinding()

    // TODO: config movement based on ship
    if(world.controls.pressed(Key.RotateLeft)) {
      body.rotate(-Maths.F_PI_2 / 64.0f)
    }

    if(world.controls.pressed(Key.RotateRight)) {
      body.rotate(Maths.F_PI_2 / 64.0f)
    }

    if(world.controls.pressed(Key.Thrust)) {
      body.thrust(15.0f)
    }

    if(world.controls.pressed(Key.Reverse)) {
      body.thrust(-15.0f)
    }
  }

  override fun afterStep(delta: Float) {
    super.afterStep(delta)

    // TODO: config max speed based on ship
    body.clampSpeed(max = 80.0f)
  }

  private fun resolveWayfinding() {
    val candidate = world.session.route
    if(candidate != null && candidate != wayfindingRoute) {
      beginWayfinding(candidate)
    }

    val route = route?.let { it } ?: return
    val point = route[routeIndex]

    // move towards wayfinding point
    val angle    = position.angleTo(point)
    val position = position.lerp(point, 0.5f)
    body.setTransform(position, angle)

    // if the ship is close enough, advance to the next point
    if(body.position.dst2(point) < 0.01) {
      routeIndex++

      // once every point has been hit, finish
      if(routeIndex >= route.size) {
        finishWayfinding()
      }
    }
  }

  private fun beginWayfinding(currentRoute: Route) {
    wayfindingRoute = currentRoute
    route = currentRoute.points.map(projector(from = normal, to = Projections.world))
    routeIndex = 0
  }

  private fun finishWayfinding() {
    wayfindingRoute = null
    route = emptyList()
    world.session.advance()
  }

  // MARK: Minimap
  override fun configure(node: MinimapNode) {
    super.configure(node)
    node.color = Color.GREEN
  }

  // MARK: Targetable
  override val position: Vector2 get() = center
}
