package com.syaainn.dailic.presentation.ui.score

import com.syaainn.dailic.presentation.util.base.UiEvent
import com.syaainn.dailic.presentation.util.base.UiSideEffect
import com.syaainn.dailic.presentation.util.base.UiState

class ScoreContract {
    data class State(
        val score: List<Boolean> = listOf(),
        val showExitDialog: Boolean = false,
    ): UiState {
        val totalQuestion: Int = score.size
        val correctQuestion: Int = score.count { it }
    }

    sealed class Event: UiEvent {
        data class SetScore(val score: List<Boolean>): Event()
        data object OnBackClick: Event()
        data object OnConfirmExitDialog: Event()
        data object OnDismissExitDialog: Event()
        data object OnAdditionalStudyClick: Event()
    }

    sealed interface SideEffect: UiSideEffect {
        data object NavigateToHome: SideEffect
        data object NavigateToAdditionalStudy: SideEffect
    }
}