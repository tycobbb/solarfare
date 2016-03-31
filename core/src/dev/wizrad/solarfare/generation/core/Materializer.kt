package dev.wizrad.solarfare.generation.core

interface Materializer {
  fun <N: Node, P: Node> materialize(node: N, parent: Materializable<P>?): Materializable<N>
}
