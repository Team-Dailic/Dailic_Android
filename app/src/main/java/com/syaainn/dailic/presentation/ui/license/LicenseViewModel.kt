package com.syaainn.dailic.presentation.ui.license

import androidx.lifecycle.viewModelScope
import com.syaainn.dailic.data.dto.request.SelectLicenseRequestDto
import com.syaainn.dailic.data.service.LicenseService
import com.syaainn.dailic.presentation.model.License
import com.syaainn.dailic.presentation.model.Occupation
import com.syaainn.dailic.presentation.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class LicenseViewModel @Inject constructor(
    private val licenseService: LicenseService
) : BaseViewModel<LicenseContract.State, LicenseContract.Event, LicenseContract.SideEffect>() {

    override fun createInitialState(): LicenseContract.State = LicenseContract.State()

    override suspend fun handleEvent(event: LicenseContract.Event) {
        when (event) {
            is LicenseContract.Event.OnLicenseClick -> {
                setState { copy(selectedLicense = event.selectedLicense) }
            }
            is LicenseContract.Event.OnCompleteClick -> {
                selectLicense(
                    occupation = event.selectedOccupation,
                    license = currentState.selectedLicense ?: License.DRIVING
                )
            }
        }
    }

    fun sendSideEffect(sideEffect: LicenseContract.SideEffect) = setSideEffect(sideEffect)

    private fun selectLicense(occupation: Occupation, license: License) {
        viewModelScope.launch {
            runCatching {
                licenseService.selectLicense(
                    requestBody = SelectLicenseRequestDto(
                        occupation = occupation.name,
                        license = license.title
                    )
                )
            }.fold(
                onSuccess = { response ->
                    if (response.status == 200) {
                        setSideEffect(LicenseContract.SideEffect.NavigateToHome)
                    } else {
                        Timber.tag("SelectLicense Api").d(
                            "SelectLicense Api Success But : ${response.status}, ${response.message}"
                        )
                    }
                },
                onFailure = {
                    Timber.tag("SelectLicense Api").d("SelectLicense Api Failure : ${it.message}")
                }
            )
        }
    }
}
