package com.syaainn.dailic.presentation.ui.additionalstudy

import com.syaainn.dailic.presentation.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdditionalStudyViewModel @Inject constructor(

): BaseViewModel<AdditionalStudyContract.State, AdditionalStudyContract.Event, AdditionalStudyContract.SideEffect>() {

    override fun createInitialState(): AdditionalStudyContract.State = AdditionalStudyContract.State()

    override suspend fun handleEvent(event: AdditionalStudyContract.Event) {
        when (event) {
            is AdditionalStudyContract.Event.OnBackClick -> {
                setState { copy(showExitDialog = true) }
            }
            is AdditionalStudyContract.Event.OnDismissExitDialog -> {
                setState { copy(showExitDialog = false) }
            }
            is AdditionalStudyContract.Event.OnConfirmExitDialog -> {
                setState { copy(showExitDialog = false) }
                setSideEffect(AdditionalStudyContract.SideEffect.NavigateToHome)
            }
            is AdditionalStudyContract.Event.OnScrapClick -> {

            }
            is AdditionalStudyContract.Event.OnPreviousQuestionClick -> {
                setState { copy(currentQuestionNum = currentQuestionNum - 1) }
            }
            is AdditionalStudyContract.Event.OnNextQuestionClick -> {
                setState { copy(currentQuestionNum = currentQuestionNum + 1) }
            }
            is AdditionalStudyContract.Event.OnCompleteClick -> {
                setState { copy(showCompleteDialog = true) }
            }
            is AdditionalStudyContract.Event.OnDismissCompleteDialog -> {
                setState { copy(showCompleteDialog = false) }
            }
            is AdditionalStudyContract.Event.OnConfirmCompleteDialog -> {
                setState { copy(showCompleteDialog = false) }
                setSideEffect(AdditionalStudyContract.SideEffect.NavigateToHome)
            }
        }
    }

    fun sendSideEffect(sideEffect: AdditionalStudyContract.SideEffect) = setSideEffect(sideEffect)
}