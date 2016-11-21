package dev.wizrad.solarfare.support.extensions

import io.reactivex.Observable
import io.reactivex.disposables.Disposable

fun <T> Observable<T>.subskribe(
  next:      (T) -> Unit = {},
  error:     (Throwable) -> Unit = {},
  completed: () -> Unit = {}): Disposable {

  return subscribe(next, error, completed)
}
