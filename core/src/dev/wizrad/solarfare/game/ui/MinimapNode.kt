package dev.wizrad.solarfare.game.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import dev.wizrad.solarfare.game.shared.CoordinateSpace.Kind
import dev.wizrad.solarfare.game.shared.transform
import dev.wizrad.solarfare.game.ui.support.setPosition

class MinimapNode(
  private val mappable: Mappable): Actor() {

  // MARK: Properties
  val id: Int = nextId()
  val texture: TextureRegion by lazy {
    TextureRegion(Texture(Gdx.files.internal("minimap-node.png")), 1, 1)
  }

  // MARK: Lifecycle
  init {
    width  = 2.0f
    height = 2.0f
  }

  override fun act(delta: Float) {
    super.act(delta)

    val position = transform(mappable.center, from = Kind.WORLD, to = Kind.MINIMAP)
    setPosition(position)
  }

  override fun draw(batch: Batch?, parentAlpha: Float) {
    super.draw(batch, parentAlpha)
    batch?.draw(texture, x, y, originX, originY, width, height, scaleX, scaleY, rotation)
  }

  fun destroy() {
    remove()
  }

  companion object {
    private var id = 0
    private fun nextId(): Int {
      return id++
    }
  }
}
