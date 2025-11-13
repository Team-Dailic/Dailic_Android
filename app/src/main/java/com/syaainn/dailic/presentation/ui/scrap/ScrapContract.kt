package com.syaainn.dailic.presentation.ui.scrap

import com.syaainn.dailic.data.dummy.DailyStudyDummy
import com.syaainn.dailic.presentation.model.Problem
import com.syaainn.dailic.presentation.util.base.UiEvent
import com.syaainn.dailic.presentation.util.base.UiSideEffect
import com.syaainn.dailic.presentation.util.base.UiState

class ScrapContract {
    data class State(
        val scrapList: List<Problem> = DailyStudyDummy.problemLists.filter { it.isScraped == true }
    ): UiState

    sealed class Event: UiEvent {
        data object OnBackClick: Event()
    }

    sealed interface SideEffect: UiSideEffect {
        data object NavigateToBack: SideEffect
    }
}