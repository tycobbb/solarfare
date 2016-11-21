package dev.wizrad.solarfare.game.world

import dev.wizrad.solarfare.game.components.controls.Controls
import dev.wizrad.solarfare.game.components.session.Session
import dev.wizrad.solarfare.game.ui.minimap.Minimap
import com.badlogic.gdx.physics.box2d.World as PhysicsWorld

interface World {
  val physics:  PhysicsWorld get
  val session:  Session get
  val controls: Controls get
  val minimap:  Minimap get
}
