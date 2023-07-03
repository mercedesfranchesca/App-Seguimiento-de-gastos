package com.example.appseguimientogastos

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appseguimientogastos.data.getCurrentMonth
import com.example.appseguimientogastos.ui.compose.CustoNavigationDrawer
import com.example.appseguimientogastos.ui.compose.MainComposeAppBar
import com.example.compose.AppSeguimientoGastosTheme

class MainComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainComposeApp()
        }

    }
}

@Composable
fun MainComposeApp(
    modifier: Modifier = Modifier,
) {
    AppSeguimientoGastosTheme {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        // list of nav screens + icons
        val currentMonth = remember { mutableStateOf(getCurrentMonth()) }

        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            tabRowScreens.find { it.route == currentDestination?.route } ?: Main


        Surface(
            modifier = modifier.fillMaxSize()
        ) {

            ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
                CustoNavigationDrawer(
                    allScreens = tabRowScreens,
                    onTabSelected = { newScreen ->
                        navController.navigateSingleTopTo(newScreen.route)
                    },
                    currentScreen = currentScreen,
                    drawerState = drawerState,
                    scope = scope,
                )
            }) {
                Scaffold(
                    topBar = {
                        MainComposeAppBar(
                            drawerState = drawerState,
                            scope = scope
                        )
                    }
                ) { innerPadding ->


                    MainComposeNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        currentMonth = currentMonth,
                        allScreens = tabRowScreens,


                    )
                }
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun MainComposeAppPreview() {
    MainComposeApp()
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainComposeAppDarkPreview() {
    MainComposeApp()
}
