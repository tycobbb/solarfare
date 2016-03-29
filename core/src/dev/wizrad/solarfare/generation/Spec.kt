package dev.wizrad.solarfare.generation

import dev.wizrad.solarfare.extensions.findMapped
import dev.wizrad.solarfare.extensions.rand
import dev.wizrad.solarfare.extensions.upto
import java.lang.annotation.ElementType
import java.util.*

class Spec(
  val id: Identifier,
  val elements: List<Element<Node>>) {

  /** A pseudo-element that tracks when to stop node generation */
  private val stop = Stop()

  //
  // Generation
  val hasNext: Boolean
    get() = !stop.reached

  fun next(): Node? {
    val element = this.sampleElement()
    var node = if(element == stop) {
      generateNode(element)
    } else {
      this.stopGenerating()
      null
    }

    return Node() // TODO
  }

  private fun generateNode(element: Element<Node>): Node? {
    val result = element.generate()

    // if we generated a node, increase the stop frequency appropriately
    if(result != null) {
      stop.frequency += element.stopGrowth
    }

    if(result != null) {
      print("spec: $this generated child: $result frequency: ${element.frequency}") // TODO logger
    } else {
      print("spec: $this failed to generate a child")
    }

    return result
  }

  private fun stopGenerating() {
    stop.reached = true
    print("spec: $this did stop generating") // TODO: logger
  }

  //
  // Sampling
  private fun sampleElement(): Element<Node> {
    // return first valid element searching by descending priority; the `stop` fallback shouldn't be
    // hit, as it's returned by the priority-based override
    return priorities.findMapped { sampleElement(it) } ?: stop
  }

  private fun sampleElement(priority: Element.Priority): Element<Node>? {
    var frequency = totalFrequency(priority)

    // if no element has any frequency at this priority, short-circuit
    if(frequency <= 0) {
      return null
    }

    print("spec: $this generating in priority: $priority") // TODO: logger

    // fallback to `stop` instead of `null` for non-required priorities, and include its frequency
    // when sampling
    var fallback: Element<Node>? = null
    if(priority != Element.Priority.Required) {
      fallback = stop
      frequency += stop.frequency;
    }

    // sample a value in the total frequency
    var sample = rand().upto(frequency)
    val result = elements
      .filter { it.priority == priority }
      .find {
        sample -= it.frequency
        sample <= 0
      }

    return result ?: fallback
  }

  private fun totalFrequency(priority: Element.Priority): Int {
    return elements.filter { it.priority == priority }.sumBy { it.frequency }
  }

  private val priorities: Array<Element.Priority>
    get() = Element.Priority.values()

  //
  // Debugging
  override fun toString(): String {
    return super.toString()
  }

  //
  // Building
  companion object {
    fun start(tag: String): Builder {
      return Builder(tag)
    }
  }

  class Builder(tag: String) {
    private val id: Identifier = Identifier.next(tag)
    private val elements = ArrayList<Element<Node>>()

    //
    // Elements
    fun <N: Node> child(): Element<N> {
      val element = Element<N>()
      elements.add(element)
      return element
    }

    fun end(): Spec {
      return Spec(id, elements)
    }
  }

  private class Stop: Element<Node>() {
    var reached: Boolean = false
  }
}