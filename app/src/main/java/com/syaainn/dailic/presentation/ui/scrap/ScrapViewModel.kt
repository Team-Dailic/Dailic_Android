package com.syaainn.dailic.presentation.ui.scrap

import androidx.lifecycle.viewModelScope
import com.syaainn.dailic.data.service.ProblemService
import com.syaainn.dailic.presentation.model.Problem
import com.syaainn.dailic.presentation.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class ScrapViewModel @Inject constructor(
    private val problemService: ProblemService
) : BaseViewModel<ScrapContract.State, ScrapContract.Event, ScrapContract.SideEffect>() {

    init {
        fetchScrapList()
    }

    override fun createInitialState(): ScrapContract.State = ScrapContract.State()

    override suspend fun handleEvent(event: ScrapContract.Event) {
        when (event) {
            is ScrapContract.Event.OnBackClick -> {
                setSideEffect(ScrapContract.SideEffect.NavigateToBack)
            }
        }
    }

    fun sendSideEffect(sideEffect: ScrapContract.SideEffect) = setSideEffect(sideEffect)

    private fun fetchScrapList() {
        viewModelScope.launch {
            runCatching {
                problemService.getScrapProblem()
            }.fold(
                onSuccess = { response ->
                    setState {
                        copy(
                            scrapList = response.data.map {
                                Problem(
                                    id = it.problemId,
                                    content = it.questionText,
                                    isScraped = it.isScraped
                                )
                            }
                        )
                    }
                },
                onFailure = {
                    Timber.tag("GetScrapProblem Api").d(
                        "GetScrapProblem Api Failure : ${it.message}"
                    )
                }
            )
        }
    }
}
