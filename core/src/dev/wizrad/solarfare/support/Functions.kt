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

// MARK: Transduction
inline fun <S, M> reduce(list: List<S>, initial: M, transducer: (M, S) -> M): M {
  var memo: M = initial

  for(element in list) {
    transducer(memo, element)
  }

  return memo
}

inline fun <E, M1, M2> zip(crossinline left: (M1, E) -> M1, crossinline right: (M2, E) -> M2): (Pair<M1, M2>, E) -> Pair<M1, M2> {
  return { memo, node ->
    Pair(left(memo.first, node), right(memo.second, node))
  }
}

// MARK: Optionals
inline fun <T> T?.unwrap(crossinline closure: (T) -> Unit) {
  if(this != null) {
    closure(this)
  }
}
