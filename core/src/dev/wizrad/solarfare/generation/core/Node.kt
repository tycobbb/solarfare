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

  //
  // Lifecycle
  fun bootstrap() {
    generate()
    materialize(null as Materializable<Node>?)
  }

  private fun generate() {
    // run the spec builder and capture its id
    val localSpec= spec().end()
    id = localSpec.id

    // generate children from the spec as long as its able
    while(localSpec.hasNext) {
      val node = localSpec.next()
      if(node != null) {
        add(node)
      }
    }
  }

  /** Realizes the node in the game world, each subclass should implement this */
  protected fun <P: Node> materialize(parent: Materializable<P>?) {
    println("node: $this materializing children: ${children.count()}") // TODO: logging
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
  protected open fun spec(): Spec.Builder {
    return Spec.start(tag ?: "")
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