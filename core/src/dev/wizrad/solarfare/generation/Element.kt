package dev.wizrad.solarfare.generation

open class Element<out N: Node> {
  var priority: Priority = Priority.Required
  var frequency: Int = 0

  enum class Priority {
    Required,
    Normal;
  }

  fun generate(): N? {
    return null // TODO
  }

  //
  // Stop
  val stopGrowth: Int get() = 0
}
