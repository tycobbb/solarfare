package dev.wizrad.solarfare.generation.core

open class Node(
  val tag: String) {

  // MARK: Configuration
  var id: Identifier = Identifier.next(tag)
  /** @property resource Name for the resource to generate from this node */
  var resource: String? = null

  // MARK: Lifecycle
  init {
    // fire pre-generation hooks
    willGenerate()
  }

  fun resolve() {
    // fire post-generation hooks
    didGenerate()
  }

  protected open fun willGenerate() {
  }

  protected open fun didGenerate() {
  }

  // MARK: Accessors
  val name: String get() = id.toString()

  // MARK: Debugging
  override fun toString(): String {
    return "[node: $id]"
  }
}
