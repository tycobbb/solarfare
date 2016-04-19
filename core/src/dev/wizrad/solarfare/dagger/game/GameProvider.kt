package dev.wizrad.solarfare.dagger.game

import dev.wizrad.solarfare.config.Config

interface GameProvider {
  fun config(): Config
}
