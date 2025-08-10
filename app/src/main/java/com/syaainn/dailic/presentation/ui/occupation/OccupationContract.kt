package com.syaainn.dailic.presentation.ui.occupation

import com.syaainn.dailic.presentation.model.Occupation
import com.syaainn.dailic.presentation.util.base.UiEvent
import com.syaainn.dailic.presentation.util.base.UiSideEffect
import com.syaainn.dailic.presentation.util.base.UiState

class OccupationContract {
    data class State(
        val selectedOccupation: Occupation? = null
    ): UiState

    sealed class Event: UiEvent {
        data class OnOccupationClick(val selectedOccupation: Occupation): Event()
    }

    sealed interface SideEffect: UiSideEffect {
        data object NavigateToLicense: SideEffect
    }
}