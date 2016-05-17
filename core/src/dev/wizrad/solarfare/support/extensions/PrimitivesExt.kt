package dev.wizrad.solarfare.support.extensions

inline fun Int.repeat(crossinline closure: (Int) -> Unit) {
  for(i in 0..this-1) {
    closure(i)
  }
}
