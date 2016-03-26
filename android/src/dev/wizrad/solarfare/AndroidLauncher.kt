package dev.wizrad.solarfare

import android.os.Bundle

import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import dev.wizrad.solarfare.SolarFare

class AndroidLauncher : AndroidApplication() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val config = AndroidApplicationConfiguration()
    initialize(SolarFare(), config)
  }
}