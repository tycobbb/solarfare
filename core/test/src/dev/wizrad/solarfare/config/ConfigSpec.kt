package dev.wizrad.solarfare.config

import dev.wizrad.solarfare.support.fixture
import dev.wizrad.solarfare.support.loadGdx
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.notNullValue
import org.jetbrains.spek.api.Spek

class ConfigSpec: Spek({
  given("a config") {
    loadGdx()

    on("#load") {
      val file   = fixture("config.json")
      val input  = file.readString().replace(Regex("\\s+"), "")
      val output = Config.load(file.read()).toJson()

      it("parses the config tree") {
        assertThat(output, notNullValue())
        assertThat(input, equalTo(output))
      }
    }
  }
})
