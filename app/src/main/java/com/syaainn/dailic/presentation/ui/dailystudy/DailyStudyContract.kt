package com.syaainn.dailic.presentation.ui.dailystudy

import com.syaainn.dailic.data.dummy.DailyStudyDummy
import com.syaainn.dailic.presentation.model.Question
import com.syaainn.dailic.presentation.util.base.UiEvent
import com.syaainn.dailic.presentation.util.base.UiSideEffect
import com.syaainn.dailic.presentation.util.base.UiState

class DailyStudyContract {
    data class State(
        val currentQuestionNum: Int = 1,
        val totalQuestionNum: Int = 20,
        val todayQuestions: List<Question> = DailyStudyDummy.questionList,
        val selectedAnswer: Int? = null,
        val dailyStudyState: DailyStudyState = DailyStudyState.IDLE,
        val showExitDialog: Boolean = false,
        val showFinishDialog: Boolean = false,
    ): UiState {
        val progress: Float = currentQuestionNum.toFloat() / totalQuestionNum
        val progressWeight = progress.coerceIn(0.0001f, 0.9999f)
    }

    sealed class Event: UiEvent {
        data object OnBackClick: Event()
        data object OnDismissExitDialog: Event()
        data object OnConfirmExitDialog: Event()
        data object OnScrapClick: Event()
        data class OnAnswerClick(val optionNum: Int): Event()
        data object OnSubmitClick: Event()
        data object OnNextQuestionClick: Event()
        data object OnFinishClick: Event()
        data object OnDismissFinishDialog: Event()
        data object OnConfirmFinishDialog: Event()
    }

    sealed interface SideEffect: UiSideEffect {
        data object NavigateToBack: SideEffect
        data object NavigateToHome: SideEffect
    }
}