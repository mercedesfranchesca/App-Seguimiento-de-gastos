package com.example.appseguimientogastos.ui.compose

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appseguimientogastos.R
import com.example.compose.AppSeguimientoGastosTheme
import com.example.compose.md_theme_light_Ahorro
import com.example.compose.md_theme_light_gastos
import com.example.compose.md_theme_light_ingreso


@Composable
fun MainComposeApp(
    modifier: Modifier = Modifier
) {

    AppSeguimientoGastosTheme {
        Surface(
            modifier = modifier.fillMaxSize()
        ) {
            Column(modifier.padding(dimensionResource(id = R.dimen.default_padding))) {

                DashBoardCard(modifier)

                IncomeCard(modifier)

                ExpensesCard(modifier)

                SavingsCard(modifier)

            }


        }


    }
}

/**
 * Card de la Dashboard
 * */
@Composable
fun DashBoardCard(modifier: Modifier = Modifier) {
    Row(modifier.padding(dimensionResource(id = R.dimen.default_padding))) {
        Card {
            Column(
                modifier = modifier
                    .padding(dimensionResource(id = R.dimen.default_padding))
            ) {
                Text(
                    text = stringResource(R.string.dashboard) + ":",
                    style = MaterialTheme.typography.displayLarge
                )
                Row(modifier = modifier.padding(dimensionResource(id = R.dimen.default_padding))) {
                    val progressList: List<Float> = listOf(0.2f, 0.4f, 0.4f)
                    val colorList = listOf(
                        md_theme_light_ingreso,
                        md_theme_light_gastos,
                        md_theme_light_Ahorro
                    )
                    CustomProgressBar(progressList = progressList, colorList)

                }

            }


        }
    }
}

@Composable
fun CustomProgressBar(progressList: List<Float>, colorList: List<Color>) {
    val colorScheme = MaterialTheme.colorScheme
    Canvas(modifier = Modifier.size(200.dp)) {
        val strokeWidth = 30f
        var startAngle = -90f
        drawArc(
            color = colorScheme.onSurface.copy(alpha = 0.12f),
            startAngle = startAngle,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(width = strokeWidth)
        )
        progressList.forEachIndexed { index, progress ->
            val sweepAngle = progress * 360f
            drawArc(
                color = colorList[index],
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth)
            )
            startAngle += sweepAngle
        }
    }
}


/**
 * Card de Ingresos
 * */
@Composable
fun IncomeCard(modifier: Modifier = Modifier) {
    Row(modifier.padding(dimensionResource(id = R.dimen.default_padding))) {
        OverviewCard(modifier, stringResource(R.string.ingresos) + ":")
    }


}

/**
 * Card de Gastos
 * */
@Composable
fun ExpensesCard(modifier: Modifier = Modifier) {

    Row(modifier.padding(dimensionResource(id = R.dimen.default_padding))) {
        OverviewCard(modifier, stringResource(R.string.gastos) + ":")
    }
}

/**
 * Card de Ahorro
 * */
@Composable
fun SavingsCard(modifier: Modifier = Modifier) {

    Row(modifier.padding(dimensionResource(id = R.dimen.default_padding))) {
        OverviewCard(modifier, stringResource(R.string.ahorro) + ":")
    }
}

/**
 * Plantilla de Card
 * */
@Composable
fun OverviewCard(modifier: Modifier = Modifier, title: String) {
    Card {
        Column(
            modifier = modifier
                .padding(dimensionResource(id = R.dimen.default_padding))
        ) {
            Text(text = title, style = MaterialTheme.typography.displayLarge)
            Text(text = "0.00 €", style = MaterialTheme.typography.displayMedium)
            Text(
                text = stringResource(R.string.resumen) + ":",
                style = MaterialTheme.typography.displaySmall
            )


        }
        Column(
            modifier = modifier
                .padding(dimensionResource(id = R.dimen.default_padding))
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Boton")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MainComposeAppPreview() {
    MainComposeApp()

}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MainComposeAppDarkPreview() {
    MainComposeApp()

}