package dev.wizrad.solarfare.game.components.session

import dev.wizrad.solarfare.game.components.controls.Touch
import dev.wizrad.solarfare.game.components.route.Route
import io.reactivex.Observable

class Turn(
  routes: Observable<Route>) {

  // MARK: Properties
  var state = State.Planning
  val routes: Observable<Route>

  // MARK: Accessors
  val isRunning: Boolean get() = state == State.Running

  // MARK: Intialization
  init {
    this.routes = takeNext(routes = routes)
  }

  private fun takeNext(routes: Observable<Route>): Observable<Route> {
    return routes
      .doOnNext {
        if(it.event == Touch.Event.Ended) {
          state = State.Running
        }
      }
  }

  // MARK: Types
  enum class State { Planning, Running, Finished }
}
