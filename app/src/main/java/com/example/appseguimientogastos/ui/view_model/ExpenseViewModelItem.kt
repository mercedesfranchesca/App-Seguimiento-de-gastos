package com.example.appseguimientogastos.ui.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.appseguimientogastos.data.model.Month
import com.example.appseguimientogastos.data.model.getCurrentMonth
import com.example.appseguimientogastos.domain.ItemsRepository
import com.example.appseguimientogastos.ui.view_model.utils.ItemBaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ExpenseViewModelItem(itemsRepository: ItemsRepository) :
    ItemBaseViewModel(itemsRepository = itemsRepository) {


    // UI state
    val uiState = MutableStateFlow(
        MainState()
    )

    init {
        updateMainState()
    }


    init {
        updateMainState()
    }

    private fun updateMainState(
        currentMonth: MutableState<Month> = mutableStateOf(getCurrentMonth()),
        onChangeScreenCompleted: () -> Unit = {}
    ) {
        coroutinesUtils.runMain {

            uiState.value = uiState.value.copy(
                currentMonth = currentMonth,
                isLoading = true
            )

            updateBaseState {
                coroutinesUtils.runBG {


                    val incomesListByMonth =
                        getItemByMonthList(uiState.value.currentMonth, baseState.value.incomesList)
                    val expensesListByMonth =
                        getItemByMonthList(uiState.value.currentMonth, baseState.value.expensesList)
                    val savingsListByMonth =
                        getItemByMonthList(uiState.value.currentMonth, baseState.value.savingsList)

                    coroutinesUtils.runMain {

                        uiState.value = uiState.value.copy(
                            incomesListByMonth = incomesListByMonth,
                            expensesListByMonth = expensesListByMonth,
                            savingsListByMonth = savingsListByMonth,
                            isLoading = false
                        )
                        onChangeScreenCompleted()
                    }
                }
            }
        }

    }
    fun getTotal(): Double {
        return getItemByMonthList(
            uiState.value.currentMonth,
            baseState.value.expensesList
        ).sumOf { it.price }
    }

    fun onChangeScreen(onChangeScreenCompleted: () -> Unit = {}){
        updateMainState(onChangeScreenCompleted=onChangeScreenCompleted)
    }
}

