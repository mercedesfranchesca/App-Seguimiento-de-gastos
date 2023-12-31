package com.example.appseguimientogastos.ui.compose.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.data.model.Month
import com.example.appseguimientogastos.domain.model.Item
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination
import java.text.NumberFormat


@Composable
fun OverviewTitleComposable(
    modifier: Modifier = Modifier,
    title: String,
    newScreen: MainComposeDestination,
    onChangeScreen: (onChangeScreenCompleted: () -> Unit) -> Unit = {},
    onNavigateNext: () -> Unit
) {
    Row(modifier = modifier) {
        Text(text = title, style = MaterialTheme.typography.displayLarge)
        TopEndNavigationButton(
            screen = newScreen,
            onTabSelected = {
                onChangeScreen() {
                    onNavigateNext()
                }
            })
    }
}

@Composable
fun DividerComposable(modifier: Modifier = Modifier) {
    Row(
        modifier.padding(
            top = dimensionResource(id = R.dimen.default_normalpadding),
        )
    ) {
        Divider()
    }
}

@Composable
fun ContentSummaryComposable(
    modifier: Modifier = Modifier, listItemData: List<Item>, currentMonth: MutableState<Month>,
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Row() {
        Text(
            modifier = modifier.padding(top = dimensionResource(id = R.dimen.default_normalpadding)),
            text = stringResource(R.string.resumen),
            style = MaterialTheme.typography.displaySmall

        )
        Box(modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
            ExtraInfoItemButton(
                modifier = modifier,
                expanded = expanded,
                onClick = { expanded = !expanded })
        }
    }

    val newList = mutableListOf<Item>()
    listItemData.forEach { item ->
        if (item.month == currentMonth.value.name) {
            newList.add(item)
        }
    }

    if (newList.isNotEmpty()) {
        // if expanded=true show 5 first element of a list,
        if (expanded) {
            newList.take(5).forEach { item ->
                Row {
                    Text(text = item.toString())
                }
            }
        }
        // else only show the first one
        else {
            Row {
                Text(text = newList[0].toString())
            }
        }
    } else {
        Text(text = "There is no data")
    }


}


/**
 * Plantilla de Card
 * */
@Composable
fun OverviewCard(
    modifier: Modifier = Modifier,
    title: String,
    currentMonth: MutableState<Month>,
    newScreen: MainComposeDestination,
    listItemData: List<Item>,
    total: Double,
    onChangeScreen: (onChangeScreenCompleted: () -> Unit) -> Unit,
    onNavigateNext: () -> Unit,
) {

    ElevatedCard(
        modifier = modifier.padding(bottom = dimensionResource(id = R.dimen.default_normalpadding))

    ) {
        Column(
            modifier = modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(
                modifier = modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))

            ) {
                // Title
                OverviewTitleComposable(
                    title = title,
                    newScreen = newScreen,
                    onChangeScreen = onChangeScreen,
                    onNavigateNext = onNavigateNext
                )

                //Current Money Value
                Text(text = NumberFormat.getCurrencyInstance().format(total), style = MaterialTheme.typography.displayMedium)

                DividerComposable()

                ContentSummaryComposable(listItemData = listItemData, currentMonth = currentMonth)

            }
        }

    }
}


@Composable
fun TopEndNavigationButton(
    modifier: Modifier = Modifier,
    screen: MainComposeDestination,
    onTabSelected: (MainComposeDestination) -> Unit
) {
    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
        IconButton(
            onClick = { onTabSelected(screen) }
        ) {
            Icon(
                Icons.Outlined.ArrowForward,
                contentDescription = "Localized description"
            )
        }
    }
}

@Composable
fun AddButton(
    modifier: Modifier = Modifier,
    onTabSelected: () -> Unit
) {
    Box(
        modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.default_normalpadding)),
        contentAlignment = Alignment.TopEnd
    ) {
        FloatingActionButton(
            onClick = onTabSelected,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
        }
    }
}


@Composable
private fun ExtraInfoItemButton(
    expanded: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    IconButton(modifier = modifier, onClick = onClick) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = ""
        )
    }
}

