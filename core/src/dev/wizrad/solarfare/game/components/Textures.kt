package dev.wizrad.solarfare.game.components

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion

class Textures {
  // MARK: Properties
  private val atlas by lazy { TextureAtlas(Gdx.files.internal("textures.atlas")) }

  // MARK: Game Textures
  val ship   by lazy { find("ship") }
  val star   by lazy { find("star") }
  val planet by lazy { find("planet") }

  // MARK: Interface Textures
  val minimap     by lazy { find("minimap") }
  val minimapNode by lazy { find("minimap-node") }

  // MARK: Helpers
  private fun find(region: String): TextureRegion {
    return transform(atlas.findRegion(region))
  }

  private fun transform(region: TextureRegion): TextureRegion {
    // textures have a reflected y-axis
    region.flip(false, true)
    return region
  }
}
