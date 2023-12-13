package com.example.expense_tracker.composables.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.example.expense_tracker.colors.AppColors
import com.example.expense_tracker.composables.custom_composables.CustomText
import com.example.expense_tracker.composables.custom_composables.VerticalSpace
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollState
import com.patrykandpatrick.vico.compose.component.lineComponent
import com.patrykandpatrick.vico.compose.component.shape.shader.fromBrush
import com.patrykandpatrick.vico.compose.style.ChartStyle
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.axis.Axis
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.component.text.TextComponent
import com.patrykandpatrick.vico.core.component.text.textComponent
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry

@Composable
fun MonthlyExpenseGraph(
    bottomLabel: String
) {

    val refreshDataset = remember {
        mutableIntStateOf(0)
    }

    val modelProducer = remember {
        ChartEntryModelProducer()
    }

    val datasetForModel = remember {
        mutableStateListOf(listOf<FloatEntry>())
    }

    val datasetLineSpec = remember {
        arrayListOf<LineChart.LineSpec>()
    }

    val scrollState = rememberChartScrollState()

    val marker = rememberMarker()

    LaunchedEffect(key1 = refreshDataset.intValue) {
        datasetForModel.clear()
        datasetLineSpec.clear()
        var xPos = 0f
        val dataPoints = arrayListOf<FloatEntry>()
        datasetLineSpec.add(
            LineChart.LineSpec(
                lineColor = AppColors.ButtonColorGradiant1.toArgb(),
                lineBackgroundShader = DynamicShaders.fromBrush(
                    brush = Brush.verticalGradient(
                        listOf(
                            AppColors.ButtonColorGradiant1.copy(com.patrykandpatrick.vico.core.DefaultAlpha.LINE_BACKGROUND_SHADER_START),
                            AppColors.ButtonColorGradiant1.copy(com.patrykandpatrick.vico.core.DefaultAlpha.LINE_BACKGROUND_SHADER_END)
                        )
                    )
                )
            )
        )
        for (i in 1..100) {
            val randomYFloat = (1..1000).random().toFloat()
            dataPoints.add(FloatEntry(x = xPos, y = randomYFloat))
            xPos += 1f
        }
        datasetForModel.add(dataPoints)
        modelProducer.setEntries(datasetForModel)
    }

    Column(modifier = Modifier
        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        if (datasetForModel.isNotEmpty())
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(300.dp), elevation = 0.dp) {
                ProvideChartStyle {
                    Chart(
                        chart = lineChart(
                            lines = datasetLineSpec
                        ),
                        marker = marker,
                        chartModelProducer = modelProducer,
                        chartScrollState = scrollState,
                        isZoomEnabled = true,
                        startAxis = startAxis(
                            tickLength = 0.dp,
                            valueFormatter = { value, _ ->
                                value.toInt().toString()
                            },
                            maxLabelCount = 6,
                            label = textComponent{
                                this.color = AppColors.ButtonColorGradiant1.toArgb()
                            },
                            titleComponent = textComponent{
                                this.color = AppColors.ButtonColorGradiant1.toArgb()
                            }
                        ),
                        bottomAxis = bottomAxis(
                            title = bottomLabel,
                            tickLength = 0.dp,
                            valueFormatter = { value, _ ->
                                ((value.toInt()) + 1).toString()
                            },
                            guideline = null,
                            label = textComponent{
                                this.color = AppColors.ButtonColorGradiant1.toArgb()
                            }, titleComponent = textComponent{
                                this.color = AppColors.ButtonColorGradiant1.toArgb()
                            }
                        )
                    )
                }
            }

    }

}
