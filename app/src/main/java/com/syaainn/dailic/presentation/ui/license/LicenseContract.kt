package com.syaainn.dailic.presentation.ui.license

import com.syaainn.dailic.presentation.model.License
import com.syaainn.dailic.presentation.model.Occupation
import com.syaainn.dailic.presentation.util.base.UiEvent
import com.syaainn.dailic.presentation.util.base.UiSideEffect
import com.syaainn.dailic.presentation.util.base.UiState

class LicenseContract {
    data class State(
        val selectedLicense: License? = null
    ) : UiState

    sealed class Event : UiEvent {
        data class OnLicenseClick(val selectedLicense: License) : Event()
        data class OnCompleteClick(val selectedOccupation: Occupation) : Event()
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateToBack : SideEffect
        data object NavigateToHome : SideEffect
    }
}
