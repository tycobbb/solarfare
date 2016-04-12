package dev.wizrad.solarfare.generation.core

import dev.wizrad.solarfare.support.Tag
import dev.wizrad.solarfare.support.debug
import java.awt.Point
import java.util.*

open class Node(
  val tag: String) {

  // Configuration
  /** @property resource Name for the resource to generate from this node */
  var resource: String? = null

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

  protected open fun generate() {
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
    debug(Tag.GENERATION, "$this materializing children -> ${children.count()}")
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
  // Debugging
  override fun toString(): String {
    return "[node: $id]"
  }

  //
  // Bootstrapping
  companion object {
    fun <N: Node> generate(factory: () -> N): N {
      val instance = factory()
      instance.generate()
      return instance
    }
  }
}