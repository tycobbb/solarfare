package dev.wizrad.solarfare.generation.core

import dev.wizrad.solarfare.support.SharedExamples
import dev.wizrad.solarfare.support.until
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.jetbrains.spek.api.Spek

class SpecSpec: Spek({
  var sharedExamples = SharedExamples()

  given("a spec") {
    var subject: Spec? = null

    fun subject(initializer: (Spec.Builder.() -> Unit) = {}): Spec {
      subject = Spec.start("test").apply { initializer() }.end()
      return subject!!
    }

    on("#next") {
      context("normally") {
        var result: Node? = null

        beforeEach {
          subject {
            child { Node("child") }.weight(1).decay(1)
          }

          result = subject?.next()
        }

        it("generates a node") {
          assertThat(result?.tag, `is`("child"))
        }
      }

      context("when it has a required node") {
        var result: Node? = null

        beforeEach {
          subject {
            child { Node("required") }.require(1)
            child { Node("normal") }.weight(1)
          }

          result = subject?.next()
        }

        it("generates them first") {
          assertThat(result?.tag, equalTo("required"))
        }

        context("and the requirements are satisfied") {
          var other: Node? = null
          beforeEach { other = subject?.next() }

          it("generates normal nodes") {
            assertThat(other?.tag, equalTo("normal"))
          }
        }
      }

      sharedExamples.named("it stops generating") {
        var result: Node? = null

        beforeEach {
          result = subject?.next()
        }

        it("returns null") {
          assertThat(result, `is`(nullValue()))
        }

        it("no longer generates nodes") {
          assertThat(subject?.hasNext, `is`(false))
        }
      }

      context("when generating indeterminate children") {
        beforeEach {
          subject {
            child { Node("child") }.weight(3).decay(1)
          }

          until(null) { subject?.next() }
        }

        sharedExamples.run(this, "it stops generating")
      }

      context("when it has no children") {
        beforeEach {
          subject()
        }

        sharedExamples.run(this, "it stops generating")
      }
    }
  }
})
