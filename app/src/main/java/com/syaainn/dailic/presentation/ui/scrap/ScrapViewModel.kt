package com.syaainn.dailic.presentation.ui.scrap

import com.syaainn.dailic.presentation.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScrapViewModel @Inject constructor(

): BaseViewModel<ScrapContract.State, ScrapContract.Event, ScrapContract.SideEffect>() {

    override fun createInitialState(): ScrapContract.State = ScrapContract.State()

    override suspend fun handleEvent(event: ScrapContract.Event) {
        when (event) {
            is ScrapContract.Event.OnBackClick -> {
                setSideEffect(ScrapContract.SideEffect.NavigateToBack)
            }
        }
    }

    fun sendSideEffect(sideEffect: ScrapContract.SideEffect) = setSideEffect(sideEffect)
}