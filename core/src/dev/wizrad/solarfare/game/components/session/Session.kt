package dev.wizrad.solarfare.game.components.session

import dev.wizrad.solarfare.game.components.route.Route
import dev.wizrad.solarfare.game.components.route.RouteProvider
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class Session(
  val routes: RouteProvider) {

  // MARK: Properties
  private val turns = BehaviorSubject.create<Turn>()
  private val turn: Turn get() = turns.value

  // MARK: Accessors
  val isRunning: Boolean get() = turn.isRunning

  // MARK: Observables
  val currentRoute: Observable<Route> get() = turns
    .switchMap { it.routes }

  // MARK: Operations
  fun advance() {
    val turn = Turn(routes = routes.stream)
    turns.onNext(turn)
  }
}
