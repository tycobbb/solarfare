package dev.wizrad.solarfare.generation.core

open class SpecElement<out N: Node>(
  val factory: () -> N) {

  enum class Priority {
    Required,
    Normal;
  }

  /** @property priority The priority of this element; elements are generated in descending priority-order */
  var priority = Priority.Normal
  /** @property weight The relative weight of this element for sampling purposes */
  var weight: Int = 0

  // the number of times this element generated a node
  private var timesGenerated = 0
  // the rate at at which the weight decays as a function of number of elements generated
  private var decayFunction: (Int) -> Int = { 0 }
  // an optional filter to short-circuit generating a particular node
  private var filterFunction: ((N) -> Boolean)? = null
  // an optional handler to call after a node is generated
  private var afterGenerateHandler: ((N) -> Unit)? = null

  // MARK: Generation

  /**
   Generates a new [node][Node] from this element.

   If the generated node passes the *filter*, it generates successfully and the element's *weight*
   decays according to the specified function.

   If it fails, this element generates no further nodes and *null* is returned.

   @return A new node from this element, or *null*
  */
  fun generate(): N? {
    val node   = Node.generate(factory)
    val result = finishGenerating(node)
    return result
  }

  private fun finishGenerating(node: N): N? {
    var result: N? = node
    val filtered = if(filterFunction != null) !filterFunction!!(node) else false

    if(filtered) {
      cancelGenerating(node)
      result = null
    } else {
      succeedGenerating(node)
    }

    return result
  }

  private fun succeedGenerating(node: N) {
    timesGenerated++

    // decay element weight when successful
    val decay = decayFunction(timesGenerated)
    val updatedWeight = Math.max(weight - decay, 0)

    lastDecay = weight - updatedWeight
    weight = updatedWeight

    // call handlers
    afterGenerateHandler?.invoke(node)
  }

  private fun cancelGenerating(node: N) {
    // we're not going to generate any more nodes in this element if we fail
    // TODO: this should probably be removed once cost system is implemented
    weight = 0;
  }

  // MARK: Builder
  fun prioritize(priority: Priority): SpecElement<N> {
    this.priority = priority
    return this
  }

  fun require(count: Int): SpecElement<N> {
    return prioritize(Priority.Required).weight(count).decay(1)
  }

  fun weight(weight: Int): SpecElement<N> {
    this.weight = weight
    return this
  }

  fun decay(function: (Int) -> Int): SpecElement<N> {
    decayFunction = function
    return this
  }

  fun decay(value: Int): SpecElement<N> {
    return decay { value }
  }

  fun filter(function: (N) -> Boolean): SpecElement<N> {
    filterFunction = function
    return this
  }

  fun afterGenerate(handler: (N) -> Unit): SpecElement<N> {
    afterGenerateHandler = handler
    return this
  }

  // MARK: Stop
  val stopGrowth: Int get() = if(affectsStopGrowth) lastDecay else 0
  // the last amount the weight decayed by
  private var lastDecay: Int = 0
  // whether or not the element should increase the stop weight
  private val affectsStopGrowth: Boolean get() = priority != Priority.Required
}
