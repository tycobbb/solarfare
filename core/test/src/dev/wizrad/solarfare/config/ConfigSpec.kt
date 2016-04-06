package dev.wizrad.solarfare.config

import dev.wizrad.respek.graph.Respek
import dev.wizrad.solarfare.support.fixture
import dev.wizrad.solarfare.support.loadGdx
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*

class ConfigSpec: Respek() { init {
  given("a config") {
    loadGdx()

    var input:  String? = null
    var output: String? = null

    on("#load") {
      before {
        val file = fixture("config.json")
        input  = file.readString().replace(Regex("\\s+"), "")
        output = Config.load(file.read()).toJson()
      }

      it("parses the config tree") {
        assertThat(output, notNullValue())
        assertThat(input, equalTo(output))
      }
    }
  }
}}
