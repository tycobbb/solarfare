package dev.wizrad.solarfare.game.components.route

import dev.wizrad.solarfare.game.components.controls.Controls
import dev.wizrad.solarfare.game.components.controls.Touch
import dev.wizrad.solarfare.game.core.Targetable
import io.reactivex.Observable
import javax.inject.Inject

class RouteProvider @Inject constructor(
  controls: Controls) {

  var target: Targetable? = null
  val stream: Observable<Route> = controls.touches
    .filter { it.event == Touch.Event.Began }
    .flatMap { buildRouteFrom(controls.touches) }

  private fun buildRouteFrom(touches: Observable<Touch>): Observable<Route> {
    return touches
      .scan(Route(target), Route::append)
      .takeUntil<Route> { it.event == Touch.Event.Ended }
  }
}
