package com.syaainn.dailic.presentation.ui.home

import com.syaainn.dailic.data.dummy.HomeDummy
import com.syaainn.dailic.presentation.model.License
import com.syaainn.dailic.presentation.model.Occupation
import com.syaainn.dailic.presentation.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): BaseViewModel<HomeContract.State, HomeContract.Event, HomeContract.SideEffect>() {

    override fun createInitialState(): HomeContract.State = HomeContract.State()

    override suspend fun handleEvent(event: HomeContract.Event) {
        when(event) {
            is HomeContract.Event.SetLicense -> {
                setLicense()
            }
            is HomeContract.Event.ShowLicenseSelector -> {
                setState { copy(showChangeLicenseBottomSheet = true) }
            }
            is HomeContract.Event.DismissLicenseSelector -> {
                setState { copy(showChangeLicenseBottomSheet = false, selectedOccupation = Occupation.COMMON, selectedLicense = null) }
            }
            is HomeContract.Event.OnOccupationClick -> {
                setState { copy(selectedOccupation = event.selectedOccupation, selectedLicense = null) }
            }
            is HomeContract.Event.OnLicenseClick -> {
                setState { copy(selectedLicense = event.selectedLicense) }
            }
            is HomeContract.Event.ChangeLicense -> {
                changeLicense(license = event.license)
            }
        }
    }

    fun sendSideEffect(sideEffect: HomeContract.SideEffect) = setSideEffect(sideEffect)

    private fun setLicense() {
        setState { copy(license = License.DRIVING) }
    }

    private fun changeLicense(license: License) {
        val homeDummy = HomeDummy.entries.find { it.name == license.name } ?: HomeDummy.DRIVING
        setState {
            copy(
                license = License.entries.find { it.name == homeDummy.license },
                showChangeLicenseBottomSheet = false,
                selectedOccupation = Occupation.COMMON,
                selectedLicense = null,
                total = homeDummy.total,
                solved = homeDummy.solved
            )
        }
    }
}