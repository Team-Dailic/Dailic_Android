package com.syaainn.dailic.presentation.ui.occupation

import com.syaainn.dailic.presentation.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OccupationViewModel @Inject constructor() : BaseViewModel<OccupationContract.State, OccupationContract.Event, OccupationContract.SideEffect>() {

    override fun createInitialState(): OccupationContract.State = OccupationContract.State()

    override suspend fun handleEvent(event: OccupationContract.Event) {
        when (event) {
            is OccupationContract.Event.OnOccupationClick -> {
                setState { copy(selectedOccupation = event.selectedOccupation) }
            }
        }
    }

    fun sendSideEffect(sideEffect: OccupationContract.SideEffect) = setSideEffect(sideEffect)
}
