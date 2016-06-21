package dev.wizrad.solarfare.game.world.support

import dev.wizrad.solarfare.game.core.Entity

class EntitySequence {
  // MARK: Properties
  private val collections = mutableListOf<Collection<Entity>>()

  // MARK: Builder
  fun first(entity: Entity) = then(entity)
  fun then(entity: Entity): EntitySequence {
    collections.add(listOf(entity))
    return this
  }

  fun first(entities: Collection<Entity>) = then(entities)
  fun then(entities: Collection<Entity>): EntitySequence {
    collections.add(entities)
    return this
  }

  // MARK: Output
  fun generate(): Array<Entity> {
    val size   = collections.sumBy { it.size }
    val result = arrayOfNulls<Entity>(size)

    var index = 0
    for(entities in collections) {
      for(entity in entities) {
        result[index++] = entity
      }
    }

    @Suppress("CAST_NEVER_SUCCEEDS")
    return result as Array<Entity>
  }
}
