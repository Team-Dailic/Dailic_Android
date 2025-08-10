package com.syaainn.dailic.presentation.ui.mistake

import com.syaainn.dailic.presentation.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MistakeViewModel @Inject constructor(

): BaseViewModel<MistakeContract.State, MistakeContract.Event, MistakeContract.SideEffect>() {

    override fun createInitialState(): MistakeContract.State = MistakeContract.State()

    override suspend fun handleEvent(event: MistakeContract.Event) {
        when(event) {
            is MistakeContract.Event.OnBackClick -> {
                setSideEffect(MistakeContract.SideEffect.NavigateToBack)
            }
        }
    }

    fun sendSideEffect(sideEffect: MistakeContract.SideEffect) = setSideEffect(sideEffect)
}