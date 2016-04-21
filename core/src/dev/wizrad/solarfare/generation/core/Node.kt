package dev.wizrad.solarfare.generation.core

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
  var children = ArrayList<Node>()

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

  inline fun <reified N: Node> mat(factory: (N) -> Unit) {
    if(this is N) {
      factory(this)
    }
  }

  //
  // Spec
  protected open fun spec(): Spec.Builder {
    return Spec.start(tag)
  }

  //
  // Accessors
  val name: String get() = tag

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