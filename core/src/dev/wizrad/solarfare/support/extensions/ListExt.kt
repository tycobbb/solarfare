package dev.wizrad.solarfare.support.extensions

import dev.wizrad.solarfare.extensions.rand
import dev.wizrad.solarfare.extensions.upto

/** Randomizes a copy of the list using the Fischer-Yates shuffle */
fun <E> List<E>.shuffle(): List<E> {
  val copy = toMutableList()
  copy.shuffle()
  return copy.toList()
}

/** Randomizes list in-place using the Fischer-Yates shuffle */
fun <E> MutableList<E>.shuffle() {
  // counting backwards, swap each element with a random element in the list
  for(count in (this.count()..1)) {
    // sample a random index
    val index  = count - 1
    val sample = rand().upto(count)

    // and swap the current item with the sampled one
    val swap     = this[sample]
    this[sample] = this[index]
    this[index]  = swap
  }
}

/** Replace the elements at index in place with the new `items` */
fun <E> MutableList<E>.replace(index: Int, items: Array<E>) {
  removeAt(index)
  addAll(index, items.asList())
}
