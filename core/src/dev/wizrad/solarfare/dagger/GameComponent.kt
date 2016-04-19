package dev.wizrad.solarfare.dagger

import dagger.Component
import dev.wizrad.solarfare.SolarFare
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(GameModule::class, GenerationModule::class))
interface GameComponent {
  fun inject(app: SolarFare)
}
