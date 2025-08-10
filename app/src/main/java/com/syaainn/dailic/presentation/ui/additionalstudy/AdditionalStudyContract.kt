package com.syaainn.dailic.presentation.ui.additionalstudy

import com.syaainn.dailic.data.dummy.DailyStudyDummy
import com.syaainn.dailic.presentation.model.Question
import com.syaainn.dailic.presentation.util.base.UiEvent
import com.syaainn.dailic.presentation.util.base.UiSideEffect
import com.syaainn.dailic.presentation.util.base.UiState

class AdditionalStudyContract {
    data class State(
        val currentQuestionNum: Int = 1,
        val totalQuestionNum: Int = 20,
        val todayQuestions: List<Question> = DailyStudyDummy.questionList,
        val showExitDialog: Boolean = false,
        val showCompleteDialog: Boolean = false,
    ): UiState {
        val progress: Float = currentQuestionNum.toFloat() / totalQuestionNum.toFloat()
        val progressWeight = progress.coerceIn(0.0001f, 0.9999f)
    }

    sealed class Event: UiEvent {
        data object OnBackClick: Event()
        data object OnDismissExitDialog: Event()
        data object OnConfirmExitDialog: Event()
        data object OnScrapClick: Event()
        data object OnPreviousQuestionClick: Event()
        data object OnNextQuestionClick: Event()
        data object OnCompleteClick: Event()
        data object OnDismissCompleteDialog: Event()
        data object OnConfirmCompleteDialog: Event()
    }

    sealed interface SideEffect: UiSideEffect {
        data object NavigateToHome: SideEffect
    }
}