package com.syaainn.dailic.presentation.ui.mistake

import com.syaainn.dailic.data.dummy.DailyStudyDummy
import com.syaainn.dailic.presentation.model.Problem
import com.syaainn.dailic.presentation.util.base.UiEvent
import com.syaainn.dailic.presentation.util.base.UiSideEffect
import com.syaainn.dailic.presentation.util.base.UiState

class MistakeContract {
    data class State(
        val mistakeList: List<Problem> = DailyStudyDummy.problemLists
    ): UiState

    sealed class Event: UiEvent {
        data object OnBackClick: Event()
    }

    sealed interface SideEffect: UiSideEffect {
        data object NavigateToBack: SideEffect
    }
}