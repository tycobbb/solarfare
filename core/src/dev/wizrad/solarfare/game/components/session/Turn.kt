package dev.wizrad.solarfare.game.components.session

import dev.wizrad.solarfare.game.components.controls.Touch
import dev.wizrad.solarfare.game.components.route.Route
import dev.wizrad.solarfare.support.extensions.subskribe
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

class Turn(
  routes: Observable<Route>) {

  // MARK: Properties
  var state = State.Planning
    private set
  var route: Route? = null
    private set

  // MARK: Accessors
  val isRunning: Boolean get() = state == State.Running

  // MARK: Intialization
  init {
    observe(routes)
  }

  // MARK: Lifecycle
  private fun observe(routes: Observable<Route>): Disposable {
    return routes
      .takeUntil<Route> { it.event == Touch.Event.Ended }
      .subskribe(
        next      = { route = it },
        completed = { state = State.Running }
      )
  }

  fun finish() {
    state = State.Finished
  }

  // MARK: Types
  enum class State { Planning, Running, Finished }
}
