package dev.wizrad.solarfare.generation

import dev.wizrad.respek.graph.Respek
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*

class IdentifierSpec: Respek() { init {
  given("a tag") {
    val tag = "test"
    var id: Identifier? = null

    beforeEach {
      id = Identifier.next(tag)
    }

    on("#create") {
      it("returns an id with the tag") {
        assertThat(id?.tag, equalTo(tag))
      }
    }

    on("%next") {
      var next: Identifier? = null

      beforeEach {
        next = Identifier.next(tag)
      }

      it("returns the following id in the sequence") {
        assertThat(next?.value, equalTo(id!!.value + 1))
      }

      given("another tag") {
        var other: Identifier? = null

        beforeEach {
          other = Identifier.next("other")
        }

        it("returns an id in a different sequence") {
          assertThat(other?.tag, not(equalTo(id?.tag)))
          assertThat(other?.value, equalTo(1))
        }
      }
    }
  }
}}
