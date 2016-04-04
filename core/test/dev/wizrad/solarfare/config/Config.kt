package dev.wizrad.solarfare.config

import com.badlogic.gdx.Gdx
import com.bluelinelabs.logansquare.LoganSquare
import dev.wizrad.respek.graph.Respek
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*

class ConfigSpec: Respek() { init {
  given("a config") {
    var input:  String? = null
    var output: String? = null

    on("#load") {
      before {
        input  = Gdx.files.internal("config.json").readString()
        output = LoganSquare.serialize(Config.load())
      }

      it("parses the config tree") {
        assertThat(output, notNullValue())
        assertThat(input, equalTo(output))
      }
    }
  }
}}

