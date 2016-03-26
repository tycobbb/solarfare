package dev.wizrad.solarfare

import org.robovm.apple.foundation.NSAutoreleasePool
import org.robovm.apple.uikit.UIApplication

import com.badlogic.gdx.backends.iosrobovm.IOSApplication
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration
import dev.wizrad.solarfare.SolarFare

class IOSLauncher : IOSApplication.Delegate() {
  override fun createApplication(): IOSApplication {
    val config = IOSApplicationConfiguration()
    return IOSApplication(SolarFare(), config)
  }

  companion object {
    @JvmStatic fun main(argv: Array<String>) {
      val pool = NSAutoreleasePool()
      UIApplication.main<UIApplication, IOSLauncher>(argv, null, IOSLauncher::class.java)
      pool.close()
    }
  }
}