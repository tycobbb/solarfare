package dev.wizrad.solarfare.game.components.controls

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputProcessor
import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.config.Key
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class Controls @Inject constructor(
  config: Config): InputProcessor {

  // MARK: Properties
  private val keys     = mapKeys(config.keys)
  private val _touches = PublishSubject.create<Touch>()

  val touches: Observable<Touch> = _touches.map { it }

  // MARK: Initialization
  init {
    Gdx.input.inputProcessor = this
  }

  // MARK: Evaluation
  fun pressed(key: Key): Boolean {
    val code   = checkNotNull(keys[key]) { "$this no code for $key" }
    val result = Gdx.input.isKeyPressed(code)
    return result
  }

  // MARK: Mapping
  private fun mapKeys(keys: Map<Key, String>): Map<Key, Int> {
    return keys.mapValues { entry ->
      val code = Input.Keys.valueOf(entry.value)
      check(code != -1) { "$this no mapping for $entry" }
      code
    }
  }

  // MARK: InputProcessor
  override fun keyDown(keycode: Int): Boolean {
    return false
  }

  override fun keyTyped(character: Char): Boolean {
    return false
  }

  override fun keyUp(keycode: Int): Boolean {
    return false
  }

  override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
    return false
  }

  override fun scrolled(amount: Int): Boolean {
    return false
  }

  override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
    _touches.onNext(Touch(Touch.Event.Began, screenX, screenY))
    return true
  }

  override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
    _touches.onNext(Touch(Touch.Event.Moved, screenX, screenY))
    return true
  }

  override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
    _touches.onNext(Touch(Touch.Event.Ended, screenX, screenY))
    return true
  }

  // MARK: Debugging
  override fun toString(): String {
    return "[Controls]"
  }
}
