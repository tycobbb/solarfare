package dev.wizrad.solarfare.support

fun <T> cache(function: () -> T): () -> T {
  var memo: T? = null

  return {
    if(memo == null) {
      memo = function()
    }

    memo ?: memo!!
  }
}