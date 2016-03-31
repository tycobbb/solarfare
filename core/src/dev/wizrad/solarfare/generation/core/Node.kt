package dev.wizrad.solarfare.generation.core

import java.awt.Point
import java.util.*

open class Node {
  // Configuration
  /** @property tag Name for for this node, attached to materialied object */
  private var tag: String? = null // TODO: make immutable and non-null
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
  private fun <N: Node> add(child: N): N {
    assert(!children.contains(child)) { "attempted to add existing child ${child.tag}" }

    if(child.parent != null) {
      child.parent?.remove(child)
    }

    child.parent = this
    children.add(child)

    return child
  }

  /** Removes a child, returning the removed node or null if none existed */
  private fun <N: Node> remove(child: N): N? {
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
  private fun generate() {
    // run the spec builder and capture its id
    val lspec = spec().end()
    id = lspec.id

    // generate children from the spec as long as its able
    while(lspec.hasNext) {
      val node = lspec.next()
      if(node != null) {
        add(node)
      }
    }
  }

  /** Realizes the node in the game world, each subclass should implement this */
  protected fun <P: Node> materialize(parent: Materializable<P>?) {
    print("node: $this materializing children: ${children.count()}") // TODO: logging
    for(child in children) {
      child.materialize(parent)
    }
  }

  /** Quick access to the materializer; TODO: inject this */
  protected val materializer: Materializer get() = object: Materializer {
    override fun <N : Node, P : Node> materialize(node: N, parent: Materializable<P>?): Materializable<N> {
      throw UnsupportedOperationException()
    }
  }

  //
  // Spec
  protected fun spec(spec: Spec.Builder = Spec.start(tag ?: "")): Spec.Builder {
    return spec
  }

  //
  // Accessors
  val name: String
    get() = if(tag != null) tag!! else resource!!

  //
  // Bootstrapping
  companion object {
    /** Bootstraps generation from a root node of this type */
    fun <N: Node> start(factory: () -> N): N {
      val root = generate(factory)
      var parent: Materializable<Node>? = null
      root.materialize(parent)
      return root
    }

    fun <N: Node> generate(factory: () -> N): N {
      val instance = factory()
      instance.generate()
      return instance
    }
  }
}