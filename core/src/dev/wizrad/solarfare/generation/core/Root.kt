package dev.wizrad.solarfare.generation.core

import javax.inject.Inject

class Root<N: Node> @Inject constructor(
  private val node: N) {

  fun bootstrap(): N {
    node.bootstrap()
    return node
  }
}