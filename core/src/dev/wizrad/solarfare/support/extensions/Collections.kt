package dev.wizrad.solarfare.support.extensions

inline fun <E, O> Iterable<E>.findMapped(transform: (E) -> O): O? {
  for(element in this) {
    val result = transform(element)
    if(result != null) {
      return result
    }
  }

  return null
}

inline fun <E, O> Array<out E>.findMapped(transform: (E) -> O): O? {
  for(element in this) {
    val result = transform(element)
    if(result != null) {
      return result
    }
  }

  return null
}

fun <E, C: MutableCollection<E>> C.append(element: E): C {
  this.add(element)
  return this
}
