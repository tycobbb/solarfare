package dev.wizrad.solarfare.support.collections

import dev.wizrad.solarfare.support.extensions.shuffle

class ShuffleBag<E>(
  /** The list of items to shuffle through */
  private var items: List<E>) {
  /** The next index of the sampled item */
  private var nextIndex: Int = 0
  /** `true` if the bag should automatically reshuffle during sampling */
  var reshuffles = false

  init {
    shuffle()
  }

  //
  // Operations
  fun sample(): E? {
    if(isEmpty) { return null }

    // if we've sampled each item, return null unless we auto-reshuffle
    if(isExhausted) {
      if(!reshuffles) {
        return null
      } else {
        shuffle()
      }
    }

    return items[nextIndex++]
  }

  private fun shuffle() {
    items = items.shuffle()
    nextIndex = 0
  }

  //
  // Accessors
  val isEmpty: Boolean get() = items.isEmpty()
  val isExhausted: Boolean get() = nextIndex >= items.count()
}

