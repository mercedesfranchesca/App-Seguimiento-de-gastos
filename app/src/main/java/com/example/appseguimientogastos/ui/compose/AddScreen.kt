package com.example.appseguimientogastos.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appseguimientogastos.Main
import com.example.appseguimientogastos.MainComposeDestination
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.data.Month
import com.example.appseguimientogastos.data.getCurrentMonth
import com.example.appseguimientogastos.data.item.local.Type
import com.example.appseguimientogastos.data.monthList
import com.example.appseguimientogastos.tabRowScreens
import com.example.appseguimientogastos.ui.compose.mainscreen.OverviewTitleComposable
import com.example.compose.AppSeguimientoGastosTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    currentType: Type,
    titleScreen: String,
    newScreen: MainComposeDestination,
    navController: NavHostController

) {

    var origin by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    Column(modifier = modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))) {


        Card(modifier = modifier.padding(bottom = dimensionResource(id = R.dimen.default_normalpadding))) {
            OverviewTitleComposable(
                modifier = modifier.padding(dimensionResource(id = R.dimen.default_normalpadding)),
                title = titleScreen,
                newScreen = newScreen,
                navController = navController
            )

            Divider(
                modifier.padding(
                    start = dimensionResource(id = R.dimen.default_normalpadding),
                    end = dimensionResource(id = R.dimen.default_normalpadding)
                )
            )
            Column(modifier = modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))) {
                TextField(
                    modifier = modifier.fillMaxWidth(),
                    value = origin,
                    onValueChange = { newOrigin -> origin = newOrigin },
                    label = { Text(text = "Origin") }
                )
                Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.default_smallpadding)))
                TextField(
                    modifier = modifier.fillMaxWidth(),
                    value = price,
                    onValueChange = { newPrice -> price = newPrice },
                    label = { Text(text = "Price") }
                )

                Row() {
                    var showDialog by remember { mutableStateOf(false) }
                    var selectedMonthText by remember { mutableStateOf(currentMonth.value) }

                    Column {
                        Text(text = "Selecciona un mes:")

                        FilterChip(
                            label = {
                                Text(text = selectedMonthText.name)
                            },
                            selected = true,
                            onClick = { showDialog = !showDialog },
                            trailingIcon = {
                                Icon(
                                    Icons.Filled.ArrowDropDown,
                                    contentDescription = "Localized description",
                                    Modifier.size(AssistChipDefaults.IconSize)
                                )
                            },
                            elevation = FilterChipDefaults.elevatedFilterChipElevation(),
                        )

                        DropdownMenu(
                            expanded = showDialog,
                            onDismissRequest = { showDialog = false }) {


                            monthList.forEach { monthSelected ->

                                DropdownMenuItem(
                                    text = { Text(monthSelected.name) },
                                    onClick = {
                                        selectedMonthText = monthSelected
                                        currentMonth.value = monthSelected
                                        month = monthSelected.name
                                        showDialog = false
                                    })
                                Divider()


                            }
                        }

                    }


                }


            }

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensionResource(id = R.dimen.default_normalpadding)),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { /*TODO*/ }, modifier = modifier.padding(
                            end = dimensionResource(id = R.dimen.default_smallpadding)
                        )
                    ) {
                        Text(text = stringResource(R.string.add))

                    }
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = modifier.padding(bottom = 40.dp)
                    ) {
                        Text(text = stringResource(R.string.cancel))

                    }
                }
            }
        }
    }

}

@Composable
fun AddIncomeScreen(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    newScreen: MainComposeDestination,
    navController: NavHostController
) {

    AddScreen(
        Modifier,
        currentMonth = currentMonth,
        currentType = Type.INCOMES,
        stringResource(R.string.add_incomes),
        newScreen = newScreen,
        navController = navController
    )

}

@Composable
fun AddExpensesScreen(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    newScreen: MainComposeDestination,
    navController: NavHostController
) {

    AddScreen(
        Modifier,
        currentMonth = currentMonth,
        currentType = Type.EXPENSES,
        stringResource(R.string.add_expenses),
        newScreen = newScreen,
        navController = navController
    )

}

@Composable
fun AddSavingsScreen(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    newScreen: MainComposeDestination,
    navController: NavHostController
) {

    AddScreen(
        Modifier,
        currentMonth = currentMonth,
        currentType = Type.SAVINGS,
        stringResource(R.string.add_savings),
        newScreen = newScreen,
        navController = navController
    )

}

@Composable
fun FakeAddScreen() {
    AppSeguimientoGastosTheme {
        val currentMonth = remember { mutableStateOf(getCurrentMonth()) }
        val currentType = Type.INCOMES
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            tabRowScreens.find { it.route == currentDestination?.route } ?: Main

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            AddScreen(
                Modifier,
                currentMonth = currentMonth,
                currentType = currentType,
                stringResource(R.string.add_incomes),
                newScreen = currentScreen,
                navController = navController
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FakeAppScreenPreview() {
    FakeAddScreen()
}