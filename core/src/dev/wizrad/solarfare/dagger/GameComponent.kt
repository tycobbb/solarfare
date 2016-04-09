package dev.wizrad.solarfare.dagger

import dagger.Component
import javax.inject.Singleton
import dev.wizrad.solarfare.SolarFare

@Singleton
@Component(modules = arrayOf(GameModule::class, GenerationModule::class))
interface GameComponent {
  fun inject(game: SolarFare)
}
