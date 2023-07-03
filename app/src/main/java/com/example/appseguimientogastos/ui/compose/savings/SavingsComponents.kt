package com.example.appseguimientogastos.ui.compose.savings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.appseguimientogastos.ui.MainComposeDestination
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.ui.data.Month
import com.example.appseguimientogastos.ui.data.item.local.Type
import com.example.appseguimientogastos.ui.data.monthList
import com.example.appseguimientogastos.ui.domain.item.model.Item
import com.example.appseguimientogastos.ui.compose.components.OverviewCard

/**
 * Card de Ahorro
 * */
@Composable
fun SavingsCard(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    currentMonth: MutableState<Month>,
    savingsScreen: MainComposeDestination,

    ) {

    val listIncomes = listOf<Item>(
        Item(
            id = 0,
            origin = "prueba1Savings",
            price = 12.0,
            type = Type.INCOMES.typeName,
            month = monthList[6].name
        ), Item(
            id = 0,
            origin = "prueba2Savings",
            price = 14.0,
            type = Type.INCOMES.typeName,
            month = monthList[6].name
        ), Item(
            id = 0,
            origin = "prueba3Savings",
            price = 16.0,
            type = Type.INCOMES.typeName,
            month = monthList[6].name
        ),
        Item(
            id = 0,
            origin = "prueba4Savings",
            price = 12.0,
            type = Type.INCOMES.typeName,
            month = monthList[0].name
        )
    )

    OverviewCard(
        modifier,
        stringResource(R.string.ahorro),
        currentMonth,
        savingsScreen,
        navController,
        listIncomes,
    )

}
