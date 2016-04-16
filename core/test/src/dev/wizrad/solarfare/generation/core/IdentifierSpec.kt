package dev.wizrad.solarfare.generation.core

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.not
import org.jetbrains.spek.api.Spek

class IdentifierSpec: Spek({
  given("an identifier") {
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
})
