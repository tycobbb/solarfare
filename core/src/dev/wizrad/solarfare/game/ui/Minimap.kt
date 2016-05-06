package dev.wizrad.solarfare.game.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.Stage
import dev.wizrad.solarfare.game.shared.CoordinateSpace
import dev.wizrad.solarfare.game.shared.CoordinateSpace.Kind
import dev.wizrad.solarfare.support.unwrap

class Minimap(): Group() {
  // MARK: Properties
  private val texture: TextureRegion by lazy {
    TextureRegion(Texture(Gdx.files.internal("minimap.png")), 1, 1)
  }

  init {
    width  = 100.0f
    height = 100.0f

    // setup the minimap coordinate space
    CoordinateSpace.registerTransformsFor(Kind.MINIMAP,
      byScale   = Vector2(width, height),
      transform = Vector2(0.0f,  -1.0f)
    )
  }

  // MARK: Lifecycle
  override fun setStage(stage: Stage?) {
    super.setStage(stage)

    stage.unwrap {
      val padding = 10.0f
      x = it.viewport.screenWidth  - width  - padding
      y = padding
    }
  }

  override fun draw(batch: Batch?, parentAlpha: Float) {
    super.draw(batch, parentAlpha)
    batch?.draw(texture, x, y, originX, originY, width, height, scaleX, scaleY, rotation)
  }

  // MARK: Tracking
  fun track(mappable: Mappable): MinimapNode {
    val node = MinimapNode(mappable)
    addActor(node)
    return node
  }
}
