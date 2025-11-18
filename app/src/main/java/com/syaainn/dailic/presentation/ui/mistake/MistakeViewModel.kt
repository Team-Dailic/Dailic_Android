package com.syaainn.dailic.presentation.ui.mistake

import androidx.lifecycle.viewModelScope
import com.syaainn.dailic.data.service.ProblemService
import com.syaainn.dailic.presentation.model.Problem
import com.syaainn.dailic.presentation.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class MistakeViewModel @Inject constructor(
    private val problemService: ProblemService
) : BaseViewModel<MistakeContract.State, MistakeContract.Event, MistakeContract.SideEffect>() {

    init {
        fetchMistakeList()
    }

    override fun createInitialState(): MistakeContract.State = MistakeContract.State()

    override suspend fun handleEvent(event: MistakeContract.Event) {
        when (event) {
            is MistakeContract.Event.OnBackClick -> {
                setSideEffect(MistakeContract.SideEffect.NavigateToBack)
            }
        }
    }

    fun sendSideEffect(sideEffect: MistakeContract.SideEffect) = setSideEffect(sideEffect)

    private fun fetchMistakeList() {
        viewModelScope.launch {
            runCatching {
                problemService.getWrongProblem()
            }.fold(
                onSuccess = { response ->
                    setState {
                        copy(
                            mistakeList = response.data.map {
                                Problem(
                                    id = it.problemId,
                                    content = it.questionText
                                )
                            }
                        )
                    }
                },
                onFailure = {
                    Timber.tag("GetWrongProblem Api").d(
                        "GetWrongProblem Api Failure : ${it.message}"
                    )
                }
            )
        }
    }
}
