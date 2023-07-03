package com.example.appseguimientogastos.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import com.example.appseguimientogastos.AddSavings
import com.example.appseguimientogastos.MainComposeDestination
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.data.Month
import com.example.appseguimientogastos.navigateSingleTopTo
import com.example.appseguimientogastos.ui.compose.components.AddButton
import com.example.appseguimientogastos.ui.compose.savings.SavingsCard

@Composable
fun SavingsScreen(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    navController: NavHostController,
    savingsScreen: MainComposeDestination,
) {

    LazyColumn {
        item {
            Column(
                modifier = modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))

            ) {

                SavingsCard(
                    navController = navController,
                    currentMonth = currentMonth,
                    savingsScreen = savingsScreen
                )
                val addScreen= AddSavings
                AddButton(
                    screen = addScreen,
                    onTabSelected = { newAddScreen-> navController.navigateSingleTopTo(newAddScreen.route) })


            }
        }
    }
}