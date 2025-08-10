package com.syaainn.dailic.presentation.ui.score

import com.syaainn.dailic.presentation.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(

): BaseViewModel<ScoreContract.State, ScoreContract.Event, ScoreContract.SideEffect>() {

    override fun createInitialState(): ScoreContract.State = ScoreContract.State()

    override suspend fun handleEvent(event: ScoreContract.Event) {
        when (event) {
            is ScoreContract.Event.SetScore -> {
                setState { copy(score = event.score) }
            }
            is ScoreContract.Event.OnBackClick -> {
                setState { copy(showExitDialog = true) }
            }
            is ScoreContract.Event.OnConfirmExitDialog -> {
                setState { copy(showExitDialog = false) }
                setSideEffect(ScoreContract.SideEffect.NavigateToHome)
            }
            is ScoreContract.Event.OnDismissExitDialog -> {
                setState { copy(showExitDialog = false) }
            }
            is ScoreContract.Event.OnAdditionalStudyClick -> {
                setSideEffect(ScoreContract.SideEffect.NavigateToAdditionalStudy)
            }
        }
    }

    fun sendSideEffect(sideEffect: ScoreContract.SideEffect) = setSideEffect(sideEffect)
}