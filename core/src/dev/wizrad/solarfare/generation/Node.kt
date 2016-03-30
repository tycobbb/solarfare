package dev.wizrad.solarfare.generation

import java.awt.Point
import java.util.*

open class Node {
  // Configuration
  /** @property tag Name for for this node, attached to materialied object */
  private var tag: String? = null
  /** @property resource Name for the resource to generate from this node */
  private var resource: String? = null

  // Tree
  private var id: Identifier? = null
  private var parent: Node? = null
  private var children = ArrayList<Node>()

  // Position
  private var center: Point? = null

  //
  // Relationships
  /** Adds a new child, removing it from its parent if necessary */
  fun <N: Node> add(child: N): N {
    assert(!children.contains(child)) { "attempted to add existing child ${child.tag}" }

    if(child.parent != null) {
      child.parent?.remove(child)
    }

    child.parent = this
    children.add(child)

    return child
  }

  /** Removes a child, returning the removed node or null if none existed */
  fun <N: Node> remove(child: N): N? {
    return if(children.remove(child)) child else null
  }

  /** Traverses the tree to find all descendants of type `N` */
  fun <N: Node> descendants(result: MutableList<N> = ArrayList<N>()): List<N> {
    if(this as N != null) {
      result.add(this)
    }

    for(child in children) {
      child.descendants(result)
    }

    return result
  }

  //
  // Lifecycle
  fun generate() {
    // TODO
  }

  fun materialize() {
    // TODO
  }

  //
  // Spec
  fun spec(spec: Spec.Builder): Spec.Builder {
    return spec
  }

  //
  // Bootstrapping
  companion object {
    /** Bootstraps generation from a root node of this type */
    fun <N: Node> start(): N {
      return Node() as N // TODO
    }

    fun <N: Node> generate(factory: () -> N): N {
      val instance = factory()
      instance.generate()
      return instance
    }
  }
}