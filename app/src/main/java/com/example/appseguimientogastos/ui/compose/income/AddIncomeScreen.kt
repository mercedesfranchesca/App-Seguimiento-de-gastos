package com.example.appseguimientogastos.ui.compose.income

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.data.model.Month
import com.example.appseguimientogastos.domain.model.Type
import com.example.appseguimientogastos.ui.compose.MainComposeApp
import com.example.appseguimientogastos.ui.compose.components.AddScreen
import com.example.appseguimientogastos.ui.navigation.AddIncome
import com.example.appseguimientogastos.ui.navigation.Incomes
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination
import com.example.appseguimientogastos.ui.view_model.AddViewModelItem
import com.example.appseguimientogastos.ui.view_model.BaseState
import com.example.appseguimientogastos.ui.view_model.MainState
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.compose.getViewModel

@Composable
fun AddIncomeScreenComposable(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope,
    onNavigateBack: () -> Unit,
) {
    // VIEWMODEL
    val viewModel: AddViewModelItem = getViewModel()
    val state: MainState = viewModel.uiState.collectAsState().value
    val basestate: BaseState = viewModel.baseState.collectAsState().value

    // COMPOSABLES (UI)
    val currentScreen = AddIncome
    BackHandler(enabled = true) {}
    AddIncomeScreen(
        currentMonth = state.currentMonth,
        newScreen = Incomes,
        navController = navController,
        onAddItem = viewModel::addItem,
        onChangeScreen = viewModel::onChangeScreen,
        onNavigateBack = onNavigateBack

    )
}

@Composable
fun AddIncomeScreen(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    newScreen: MainComposeDestination,
    navController: NavHostController,
    onAddItem: (origin: String, price: String, month: String, type: Type, onAddItemCompleted: () -> Unit) -> Unit,
    onChangeScreen: (onChangeScreenCompleted: () -> Unit) -> Unit,
    onNavigateBack: () -> Unit

) {

    AddScreen(
        modifier = modifier,
        currentMonth = currentMonth,
        currentType = Type.INCOMES,
        stringResource(R.string.add_incomes),
        newScreen = newScreen,
        navController = navController,
        onAddItem = onAddItem,
        onChangeScreen = onChangeScreen,
        onNavigateBack = onNavigateBack
    )

}

@Preview(showBackground = true)
@Composable
fun AddIncomeScreenPreview() {
    MainComposeApp(startDestination = AddIncome.route)
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AddIncomeScreenDarkPreview() {
    MainComposeApp(startDestination = AddIncome.route)
}
