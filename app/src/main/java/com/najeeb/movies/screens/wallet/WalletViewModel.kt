package com.najeeb.movies.screens.wallet

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class WalletViewModel : ViewModel() {
  // last-known displayed value
  private val _displayAmount = MutableStateFlow(0f)
  val displayAmount: StateFlow<Float> = _displayAmount

  // whether we've finished animating to the current target
  private val _animated = MutableStateFlow(false)
  val animated: StateFlow<Boolean> = _animated

  fun updateDisplay(value: Float) {
    _displayAmount.value = value
  }

  fun setAnimatedDone(done: Boolean) {
    _animated.value = done
  }
}
