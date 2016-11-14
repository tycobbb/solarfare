package dev.wizrad.solarfare.game.world

import com.badlogic.gdx.physics.box2d.World as PhysicsWorld
import dev.wizrad.solarfare.game.components.controls.Controls
import dev.wizrad.solarfare.game.components.route.Routes
import dev.wizrad.solarfare.game.ui.minimap.Minimap

interface World {
  val physics:  PhysicsWorld get
  val controls: Controls get
  val minimap:  Minimap get
  val routes:   Routes get
}
