package com.syaainn.dailic.presentation.ui.home

import com.syaainn.dailic.presentation.model.License
import com.syaainn.dailic.presentation.model.Occupation
import com.syaainn.dailic.presentation.util.base.UiEvent
import com.syaainn.dailic.presentation.util.base.UiSideEffect
import com.syaainn.dailic.presentation.util.base.UiState

class HomeContract {
    data class State(
        val license: License? = null,
        val showChangeLicenseBottomSheet: Boolean = false,
        val selectedOccupation: Occupation = Occupation.COMMON,
        val selectedLicense: License? = null,
        val total: Int = 0,
        val solved: Int = 0
    ) : UiState {
        val progress: Float = if (total == 0) 0f else solved.toFloat() / total.toFloat()
        val progressWeight = progress.coerceIn(0.0001f, 0.9999f)
    }

    sealed class Event : UiEvent {
        data class SetLicense(val license: License): Event()
        data object ShowLicenseSelector : Event()
        data object DismissLicenseSelector : Event()
        data class OnOccupationClick(val selectedOccupation: Occupation): Event()
        data class OnLicenseClick(val selectedLicense: License): Event()
        data class ChangeLicense(val license: License): Event()
    }

    sealed interface SideEffect: UiSideEffect {
        data object NavigateToSetting: SideEffect
        data object NavigateToDailyStudy: SideEffect
        data object NavigateToMistake: SideEffect
        data object NavigateToScrap: SideEffect
    }
}