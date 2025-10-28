package com.syaainn.dailic.presentation.ui.home

import androidx.lifecycle.viewModelScope
import com.syaainn.dailic.data.dto.request.SelectLicenseRequestDto
import com.syaainn.dailic.data.service.LicenseService
import com.syaainn.dailic.presentation.model.License
import com.syaainn.dailic.presentation.model.Occupation
import com.syaainn.dailic.presentation.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val licenseService: LicenseService
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
                changeLicense()
            }
        }
    }

    fun sendSideEffect(sideEffect: HomeContract.SideEffect) = setSideEffect(sideEffect)

    private fun setLicense() {
        viewModelScope.launch {
            runCatching { licenseService.getCurrentLicense() }.fold(
                onSuccess = { response ->
                    if(response.status == 200) {
                        setState{ copy(
                            occupation = Occupation.entries.find { it.name == response.data.occupation },
                            license = License.entries.find { it.title == response.data.license },
                            total = response.data.totalQuestion,
                            solved = response.data.solvedQuestion
                        )}
                    } else {
                        Timber.tag("SetLicense Api").d("SetLicense Api Success But : ${response.status}, ${response.message}")
                    }
                },
                onFailure = {
                    Timber.tag("SetLicense Api").d("SetLicense Api Failure : ${it.message}")
                }
            )
        }
    }

    private fun changeLicense() {
        viewModelScope.launch {
            runCatching {
                licenseService.changeLicense(requestBody = SelectLicenseRequestDto(
                    occupation = currentState.selectedOccupation.name,
                    license = currentState.selectedLicense!!.title
                ))
            }.fold(
                onSuccess = { response ->
                    if(response.status == 200) {
                        setLicense()
                        setState {
                            copy(
                                showChangeLicenseBottomSheet = false,
                                selectedOccupation = Occupation.COMMON,
                                selectedLicense = null,
                            )
                        }
                    } else {
                        Timber.tag("ChangeLicense Api").d("ChangeLicense Api Success But : ${response.status}, ${response.message}")
                    }
                },
                onFailure = {
                    Timber.tag("ChangeLicense Api").d("ChangeLicense Api Failure : ${it.message}")
                }
            )
        }
    }
}