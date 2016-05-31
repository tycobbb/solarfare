package dev.wizrad.solarfare.generation.core

import dev.wizrad.solarfare.support.extensions.clamp
import dev.wizrad.solarfare.support.extensions.generateIterable
import dev.wizrad.solarfare.support.extensions.rand
import dev.wizrad.solarfare.support.extensions.upto
import javax.inject.Provider

// MARK: Start
fun <N: Node> Node.child(provider: Provider<N>): () -> N {
  return {
    val result = provider.get()
    result.resolve()
    result
  }
}

// MARK: Multipliers
fun <N: Node> (() -> N).count(number: Int): () -> Iterable<N> {
  val generator = this

  return {
    (0..number).map { generator() }
  }
}

fun <N: Node> (() -> N).decay(from: Double, function: (Int) -> Double): () -> Iterable<N> {
  val generator = this
  var percent = (0.0..1.0).clamp(from)

  return {
    generateIterable { index ->
      val sample = rand().upto(1.0)

      if(sample <= percent) {
        percent -= function(index)
        generator()
      } else {
        null
      }
    }
  }
}

// MARK: End
fun <N: Node> (() -> N).generate(handler: (N) -> Unit = {}): N {
  val generator = this
  val node = generator()
  handler(node)
  return node
}

fun <N: Node> (() -> Iterable<N>).generate(handler: (N) -> Unit = {}): Iterable<N> {
  val generator = this
  val nodes = generator()
  nodes.forEach(handler)
  return nodes
}

