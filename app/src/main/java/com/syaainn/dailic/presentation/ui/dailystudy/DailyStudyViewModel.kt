package com.syaainn.dailic.presentation.ui.dailystudy

import com.syaainn.dailic.presentation.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DailyStudyViewModel @Inject constructor(

): BaseViewModel<DailyStudyContract.State, DailyStudyContract.Event, DailyStudyContract.SideEffect>() {

    override fun createInitialState(): DailyStudyContract.State = DailyStudyContract.State()

    override suspend fun handleEvent(event: DailyStudyContract.Event) {
        when(event) {
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
                val newAnswerList = currentState.userAnswerList.clone()
                newAnswerList[currentState.currentQuestionNum - 1] = event.optionNum
                setState { copy(userAnswerList = newAnswerList) }
            }
            is DailyStudyContract.Event.OnPreviousQuestionClick -> {
                setState { copy(currentQuestionNum = currentQuestionNum - 1) }
            }
            is DailyStudyContract.Event.OnNextQuestionClick -> {
                setState { copy(currentQuestionNum = currentQuestionNum + 1) }
            }
            is DailyStudyContract.Event.OnSubmitClick -> {
                setState { copy(showSubmitDialog = true) }
            }
            is DailyStudyContract.Event.OnDismissSubmitDialog -> {
                setState { copy(showSubmitDialog = false) }
            }
            is DailyStudyContract.Event.OnConfirmSubmitDialog -> {
                setState { copy(showSubmitDialog = false) }
                setSideEffect(DailyStudyContract.SideEffect.NavigateToScore(getScore()))
            }
        }
    }

    fun sendSideEffect(sideEffect: DailyStudyContract.SideEffect) = setSideEffect(sideEffect)

    fun getScore(): List<Boolean> {
        return currentState.userAnswerList.mapIndexed { index, userAnswer ->
            userAnswer == currentState.todayQuestions[index].answerNum
        }
    }
}