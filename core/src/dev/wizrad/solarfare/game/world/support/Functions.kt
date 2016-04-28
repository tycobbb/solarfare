package dev.wizrad.solarfare.game.world.support

import dev.wizrad.solarfare.game.world.core.NodeEntity
import dev.wizrad.solarfare.generation.core.Node

inline fun <reified N: Node, E: NodeEntity<N>> entities(crossinline factory: (N) -> E): (MutableList<E>, Node) -> MutableList<E> {
  return { memo, node ->
    if(node is N) {
      memo.add(factory(node))
    }

    memo
  }
}

fun <E1, E2> default(): Pair<MutableList<E1>, MutableList<E2>> {
  return Pair(mutableListOf(), mutableListOf())
}
