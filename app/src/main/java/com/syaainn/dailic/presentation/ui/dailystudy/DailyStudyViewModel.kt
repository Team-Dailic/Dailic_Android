package com.syaainn.dailic.presentation.ui.dailystudy

import com.syaainn.dailic.presentation.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DailyStudyViewModel @Inject constructor(

) : BaseViewModel<DailyStudyContract.State, DailyStudyContract.Event, DailyStudyContract.SideEffect>() {

    override fun createInitialState(): DailyStudyContract.State = DailyStudyContract.State()

    override suspend fun handleEvent(event: DailyStudyContract.Event) {
        when (event) {
            is DailyStudyContract.Event.OnBackClick -> {
                setState { copy(showExitDialog = true) }
            }

            is DailyStudyContract.Event.OnDismissExitDialog -> {
                setState { copy(showExitDialog = false) }
            }

            is DailyStudyContract.Event.OnConfirmExitDialog -> {
                setState { copy(showExitDialog = false) }
                setSideEffect(DailyStudyContract.SideEffect.NavigateToBack)
            }

            is DailyStudyContract.Event.OnScrapClick -> {

            }

            is DailyStudyContract.Event.OnAnswerClick -> {
                setState { copy(selectedAnswer = event.optionNum) }
            }

            is DailyStudyContract.Event.OnSubmitClick -> {
                if (currentState.currentQuestionNum == currentState.totalQuestionNum) {
                    setState { copy(dailyStudyState = DailyStudyState.FINIAL_QUESTION) }
                } else setState { copy(dailyStudyState = DailyStudyState.SUBMIT) }
            }

            is DailyStudyContract.Event.OnAiQuestionChange -> {
                setState { copy(aiQuestion = event.newValue) }
            }

            is DailyStudyContract.Event.OnNextQuestionClick -> {
                setState {
                    copy(
                        currentQuestionNum = currentQuestionNum + 1,
                        selectedAnswer = null,
                        dailyStudyState = DailyStudyState.IDLE
                    )
                }
            }

            is DailyStudyContract.Event.OnFinishClick -> {
                setState { copy(showFinishDialog = true) }
            }

            is DailyStudyContract.Event.OnDismissFinishDialog -> {
                setState { copy(showFinishDialog = false) }
            }

            is DailyStudyContract.Event.OnConfirmFinishDialog -> {
                setState { copy(showFinishDialog = false) }
                setSideEffect(DailyStudyContract.SideEffect.NavigateToHome)
            }
        }
    }

    fun sendSideEffect(sideEffect: DailyStudyContract.SideEffect) = setSideEffect(sideEffect)
}