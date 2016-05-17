package dev.wizrad.solarfare.generation.core

import java.util.*

open class Node(
  val tag: String) {

  // MARK: Configuration
  /** @property resource Name for the resource to generate from this node */
  var resource: String? = null

  // MARK: Tree
  var id: Identifier? = null
  var children = ArrayList<Node>()
  private var parent: Node? = null

  // MARK: Relationships
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

  // MARK: Lifecycle
  fun bootstrap() {
    generate()
  }

  protected open fun willGenerate() {
  }

  protected fun generate() {
    // fire pre-generation hook
    willGenerate()

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

    // fire post-generation hook
    didGenerate()
  }

  protected open fun didGenerate() {
  }

  // MARK: Spec
  protected open fun spec(): Spec.Builder {
    return Spec.start(tag)
  }

  // MARK: Accessors
  val name: String get() = id.toString()

  // MARK: Debugging
  override fun toString(): String {
    return "[node: $id]"
  }

  // MARK: Bootstrapping
  companion object {
    fun <N: Node> generate(factory: () -> N): N {
      val instance = factory()
      instance.generate()
      return instance
    }
  }
}