package dev.wizrad.solarfare.game.world

import dev.wizrad.solarfare.generation.core.Node

inline fun <reified N: Node, E: Entity<N>> entities(crossinline factory: (N) -> E): (MutableList<E>, Node) -> MutableList<E> {
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
