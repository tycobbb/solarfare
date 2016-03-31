package dev.wizrad.solarfare.generation.core

interface Materializable<N: Node> {
  fun beforeMaterialize(node: N)
  fun materialize(node: N)
}
