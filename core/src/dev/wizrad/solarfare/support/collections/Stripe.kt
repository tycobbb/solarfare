package dev.wizrad.solarfare.support.collections

import dev.wizrad.solarfare.extensions.rand
import dev.wizrad.solarfare.extensions.stride
import dev.wizrad.solarfare.support.extensions.end
import dev.wizrad.solarfare.support.extensions.length
import dev.wizrad.solarfare.support.extensions.replace
import dev.wizrad.solarfare.support.extensions.without

class Stripe(
  range: ClosedRange<Double>) {

  //
  // Properties
  /** The list of available gaps; insert can be used to block out part of a gap */
  private val gaps = mutableListOf(range)
  /** The total length of the stripe; not necessarily the available length */
  private val length = range.length
  /** `true` if the stripe has inserted at least one block */
  private var hasBlocks = false
  /** `true` if the stripe should treat the first insertion as a cap */
  private var capsFirstInsertion = true

  //
  // Convenience Constructors
  constructor(length: Double): this(0.0..length)

  //
  // Operations
  fun insert(length: Double): ClosedRange<Double>? {
    // insert a cap if necessary
    if(capsFirstInsertion && !hasBlocks) {
      return insertCap(length)
    }

    // otherwise, randomly sample our available gaps
    val bag = ShuffleBag(gaps)

    while(!bag.isExhausted) {
      val gap = bag.sample()!!

      // if we can fit the new block in this gap
      val delta = gap.length - length
      if(delta >= 0.0) {
        // generate a new block at a random position in the gap
        val position = rand().stride(gap.start, delta)
        val block    = position..length

        // and partition the gap
        partition(gap, block)

        return block
      }
    }

    return null
  }

  /** Inserts a block of `length` bounded by the stripe's maximum value; Must be first operation used on stripe */
  fun insertCap(length: Double): ClosedRange<Double> {
    assert(!hasBlocks) { "Can't insert a stripe after a block has already been inserted" }
    assert(length < this.length) { "Can't cap stripe of length ${this.length} with segment of length $length" }

    // we should have a single range at this point, so the whole strip
    val range = gaps[0]
    val cap   = (range.end - length)..range.end

    // add the cap
    partition(range, cap)

    return cap
  }

  private fun partition(gap: ClosedRange<Double>, block: ClosedRange<Double>) {
    hasBlocks = true

    // find the gap(s) remaining without the block
    val index     = gaps.indexOf(gap)
    val remainder = gap.without(block)

    // replace the old gap in place with the remainder
    gaps.replace(index, remainder)
  }

  override fun toString(): String {
    return "[stripe] gaps: ${gaps.mapIndexed { i, it -> "\ni: $it" }.joinToString("") }"
  }
}
