package dev.wizrad.solarfare.game.components.projection

class Projections {
  companion object {
    val normal = Projection(normalizer = { it }, denormalizer = { it })

    lateinit var touch:     Projection
    lateinit var screen:    Projection
    lateinit var world:     Projection
    lateinit var viewport:  Projection
    lateinit var stage:     Projection
    lateinit var stageport: Projection
    lateinit var minimap:   Projection
  }
}
