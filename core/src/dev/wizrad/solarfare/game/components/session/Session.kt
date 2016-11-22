package dev.wizrad.solarfare.game.components.session

import dev.wizrad.solarfare.game.components.route.Route
import dev.wizrad.solarfare.game.components.route.RouteProvider

class Session(
  val routes: RouteProvider) {

  // MARK: Properties
  private var turns = mutableListOf<Turn>()

  // MARK: Accessors
  val turn:  Turn   get() = turns.last()
  val route: Route? get() = turn.route

  // MARK: Lifecycle
  init {
    advance()
  }

  fun advance() {
    turns.lastOrNull()?.finish()
    turns.add(Turn(routes = routes.stream))
  }
}
