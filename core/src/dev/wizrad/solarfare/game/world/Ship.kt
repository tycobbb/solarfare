package dev.wizrad.solarfare.game.world

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import dev.wizrad.solarfare.config.Key
import dev.wizrad.solarfare.game.core.Entity
import dev.wizrad.solarfare.game.renderer.core.CameraTrackable
import dev.wizrad.solarfare.game.shared.Controls
import dev.wizrad.solarfare.game.ui.Minimap
import dev.wizrad.solarfare.game.ui.MinimapNode
import dev.wizrad.solarfare.game.world.core.NodeEntity
import dev.wizrad.solarfare.generation.ShipNode
import dev.wizrad.solarfare.support.Tag
import dev.wizrad.solarfare.support.info

class Ship(
  node:     ShipNode,
  parent:   Entity,
  minimap:  Minimap,
  controls: Controls): NodeEntity<ShipNode>(node, parent, minimap), CameraTrackable {

  // MARK: Dependencies
  private val controls = controls

  // MARK: Lifecycle
  init {
    center.set(transform(node.center))
  }

  override fun update(delta: Float) {
    super.update(delta)

    if(controls.pressed(Key.Thrust)) {
      info(Tag.General, "pressed ${Key.Thrust}")
    }
  }

  override fun configure(node: MinimapNode) {
    super.configure(node)
    node.color = Color.GREEN
  }

  // MARK: CameraTrackable
  override val point: Vector2 get() = center
}
