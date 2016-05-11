package dev.wizrad.solarfare.game.shared

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion

class Textures {
  // MARK: Properties
  private val atlas by lazy { TextureAtlas(Gdx.files.internal("textures.atlas")) }

  // MARK: Textures
  val ship        by lazy { find("ship") }
  val minimap     by lazy { find("minimap") }
  val minimapNode by lazy { find("minimap-node") }

  // MARK: Helpers
  private fun find(region: String): TextureRegion {
    return atlas.findRegion(region)
  }
}
