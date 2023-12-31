package com.example.appseguimientogastos.ui.compose.expenses

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.data.model.Month
import com.example.appseguimientogastos.domain.model.Item
import com.example.appseguimientogastos.ui.compose.components.OverviewCard
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination


/**
 * Card de Gastos
 * */
@Composable
fun ExpensesCard(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    expensesScreen: MainComposeDestination,
    listItemData: List<Item>,
    total: Double,
    onChangeScreen: (onChangeScreenCompleted: () -> Unit) -> Unit,
    onNavigateNext: () -> Unit,

    ) {


    OverviewCard(
        modifier,
        stringResource(R.string.gastos),
        currentMonth,
        expensesScreen,
        listItemData,
        total,
        onChangeScreen,
        onNavigateNext,
    )

}
