package dev.wizrad.solarfare.support

// MARK: Caching
inline fun <T> cache(crossinline function: () -> T): () -> T {
  var memo: T? = null

  return {
    if(memo == null) {
      memo = function()
    }

    memo ?: memo!!
  }
}

// MARK: Optionals
inline fun <T> T?.unwrap(crossinline closure: (T) -> Unit) {
  if(this != null) {
    closure(this)
  }
}
