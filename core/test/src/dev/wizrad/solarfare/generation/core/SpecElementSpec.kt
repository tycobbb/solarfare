package dev.wizrad.solarfare.generation.core

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.jetbrains.spek.api.Spek

class SpecElementSpec: Spek({
  given("an element") {
    var subject: SpecElement<Node>? = null

    beforeEach { subject = SpecElement { Node("test") }.weight(5) }

    on("#generate") {
      context("normally") {
        var result: Node? = null
        beforeEach { result = subject?.generate() }

        it("generates a node") {
          assertThat(result, `is`(not(nullValue())))
        }
      }

      context("with a decay") {
        beforeEach {
          subject?.decay(2)
          subject?.generate()
        }

        it("decreases the weight") {
          assertThat(subject?.weight, equalTo(3))
        }

        it("updates the stop growth") {
          assertThat(subject?.stopGrowth, equalTo(2))
        }
      }

      context("with an afterGenerate hook") {
        var hookCalled = false

        beforeEach {
          subject?.afterGenerate { hookCalled = true }
          subject?.generate()
        }

        it("called the handler") {
          assertThat(hookCalled, `is`(true))
        }
      }

      context("when the result is filtered") {
        var result: Node? = null

        beforeEach {
          subject?.filter { false }
          result = subject?.generate()
        }

        it("does not generate a node") {
          assertThat(result, `is`(nullValue()))
        }

        it("reduces the weight to 0") {
          assertThat(subject?.weight, equalTo(0))
        }
      }
    }
  }
})
