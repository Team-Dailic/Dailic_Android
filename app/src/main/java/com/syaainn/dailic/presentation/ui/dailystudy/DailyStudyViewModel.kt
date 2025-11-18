package com.syaainn.dailic.presentation.ui.dailystudy

import androidx.lifecycle.viewModelScope
import com.syaainn.dailic.data.dto.request.PostAiQuestionRequestDto
import com.syaainn.dailic.data.dto.request.PostProblemAnswerRequestDto
import com.syaainn.dailic.data.dto.request.PutScrapProblemRequestDto
import com.syaainn.dailic.data.service.ProblemService
import com.syaainn.dailic.presentation.model.Problem
import com.syaainn.dailic.presentation.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class DailyStudyViewModel @Inject constructor(
    private val problemService: ProblemService
) : BaseViewModel<DailyStudyContract.State, DailyStudyContract.Event, DailyStudyContract.SideEffect>() {

    init {
        viewModelScope.launch {
            problemService.postDailyProblem()
            delay(100L)
            runCatching {
                problemService.getDailyProblem()
            }.fold(
                onSuccess = { response ->
                    val problems = response.data.map {
                        Problem(
                            id = it.id,
                            content = it.question,
                            options = it.options,
                            answerNum = it.answer ?: 0,
                            solution = it.solution,
                            isScraped = it.isScraped
                        )
                    }
                    setState { copy(todayProblems = problems, isLoading = false) }
                },
                onFailure = {
                    Timber.tag("GetDailyProblem Api").d(
                        "GetDailyProblem Api Failure : ${it.message}"
                    )
                    setState { copy(isLoading = false) }
                }
            )
        }
    }

    override fun createInitialState(): DailyStudyContract.State = DailyStudyContract.State()

    override suspend fun handleEvent(event: DailyStudyContract.Event) {
        when (event) {
            is DailyStudyContract.Event.OnBackClick -> {
                setState { copy(showExitDialog = true) }
            }

            is DailyStudyContract.Event.OnDismissExitDialog -> {
                setState { copy(showExitDialog = false) }
            }

            is DailyStudyContract.Event.OnConfirmExitDialog -> {
                setState { copy(showExitDialog = false) }
                setSideEffect(DailyStudyContract.SideEffect.NavigateToBack)
            }

            is DailyStudyContract.Event.OnScrapClick -> {
                putScrapProblem()
            }

            is DailyStudyContract.Event.OnAnswerClick -> {
                setState { copy(selectedAnswer = event.optionNum) }
            }

            is DailyStudyContract.Event.OnSubmitClick -> {
                postProblemAnswer()
                getAiSolution()
                if (currentState.currentQuestionNum == currentState.totalQuestionNum) {
                    setState { copy(dailyStudyState = DailyStudyState.FINIAL_QUESTION) }
                } else {
                    setState { copy(dailyStudyState = DailyStudyState.SUBMIT) }
                }
            }

            is DailyStudyContract.Event.OnAiQuestionChange -> {
                setState { copy(aiQuestion = event.newValue) }
            }

            is DailyStudyContract.Event.SendAiQuestion -> {
                postAiQuestion()
            }

            is DailyStudyContract.Event.OnNextQuestionClick -> {
                setState {
                    copy(
                        currentQuestionNum = currentQuestionNum + 1,
                        selectedAnswer = null,
                        dailyStudyState = DailyStudyState.IDLE,
                    )
                }
            }

            is DailyStudyContract.Event.OnFinishClick -> {
                setState { copy(showFinishDialog = true) }
            }

            is DailyStudyContract.Event.OnDismissFinishDialog -> {
                setState { copy(showFinishDialog = false) }
            }

            is DailyStudyContract.Event.OnConfirmFinishDialog -> {
                setState { copy(showFinishDialog = false) }
                setSideEffect(DailyStudyContract.SideEffect.NavigateToHome)
            }
        }
    }

    fun sendSideEffect(sideEffect: DailyStudyContract.SideEffect) = setSideEffect(sideEffect)

    private fun postProblemAnswer() {
        viewModelScope.launch {
            runCatching {
                problemService.postProblemAnswer(
                    requestBody =
                    PostProblemAnswerRequestDto(
                        userId = 1,
                        problemId = currentState.todayProblems[currentState.currentQuestionNum - 1].id,
                        userAnswer = currentState.selectedAnswer.toString(),
                        isRetried = false
                    )
                )
            }.fold(
                onSuccess = {

                },
                onFailure = {
                    Timber.tag("PostProblemAnswer Api").d(
                        "PostProblemAnswer Api Failure : ${it.message}"
                    )
                }
            )
        }
    }

    private fun getAiSolution() {
        viewModelScope.launch {
            runCatching {
                problemService.getAiSolution(
                    problemId = currentState.todayProblems[currentState.currentQuestionNum - 1].id,
                    includeSolution = true
                )
            }.fold(
                onSuccess = { response ->
                    setState {
                        val updatedProblems = todayProblems.toMutableList()
                        val index = currentQuestionNum - 1
                        updatedProblems[index] =
                            updatedProblems[index].copy(solution = response.data.answer)

                        copy(todayProblems = updatedProblems)
                    }
                },
                onFailure = {
                    Timber.tag("getAiSolution Api").d("getAiSolution Api Failure : ${it.message}")
                }
            )
        }
    }

    private fun putScrapProblem() {
        viewModelScope.launch {
            runCatching {
                problemService.putScrapProblem(
                    requestBody =
                    PutScrapProblemRequestDto(
                        userId = 1,
                        problemId = currentState.todayProblems[currentState.currentQuestionNum - 1].id,
                        isScraped = !currentState.todayProblems[currentState.currentQuestionNum - 1].isScraped
                    )
                )
            }.fold(
                onSuccess = {
                    setState {
                        val updatedProblems = todayProblems.toMutableList()
                        val currentProblemIndex = currentQuestionNum - 1
                        if (currentProblemIndex >= 0 && currentProblemIndex < updatedProblems.size) {
                            val currentProblem = updatedProblems[currentProblemIndex]
                            updatedProblems[currentProblemIndex] = currentProblem.copy(
                                isScraped = !currentProblem.isScraped
                            )
                        }
                        copy(todayProblems = updatedProblems)
                    }
                },
                onFailure = {
                    Timber.tag("PutScrapProblem Api").d(
                        "PutScrapProblem Api Failure : ${it.message}"
                    )
                }
            )
        }
    }

    private fun postAiQuestion() {
        viewModelScope.launch {
            runCatching {
                problemService.postAiQuestion(
                    PostAiQuestionRequestDto(currentState.aiQuestion)
                )
            }.fold(
                onSuccess = {

                },
                onFailure = {

                }
            )
        }
    }
}
