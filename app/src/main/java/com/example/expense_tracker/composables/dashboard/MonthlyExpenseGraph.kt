package com.example.expense_tracker.composables.dashboard

import android.graphics.Typeface
import android.text.TextUtils
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.expense_tracker.animations.LoadingAnimation
import com.example.expense_tracker.colors.AppColors
import com.example.expense_tracker.enum.DashboardTab
import com.example.expense_tracker.firebase.ExpenseRepository
import com.example.expense_tracker.firebase.UserRepository
import com.example.expense_tracker.models.Expense
import com.example.expense_tracker.utils.Utils
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollState
import com.patrykandpatrick.vico.compose.component.shape.shader.fromBrush
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.component.text.textComponent
import com.patrykandpatrick.vico.core.dimensions.MutableDimensions
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MonthlyExpenseGraph(
    bottomLabel: String,
    viewModel: DashboardViewModel,
    tab: DashboardTab = DashboardTab.Today
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

    LaunchedEffect(key1 = tab) {

        Log.d("DashboardTab","$tab" )
        datasetForModel.clear()
        datasetLineSpec.clear()
        var dataPoints: ArrayList<FloatEntry>
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
        when (tab) {
            DashboardTab.Today -> {
                CoroutineScope(Dispatchers.IO).launch {
                    dataPoints = viewModel.getTodayDataPoints()

                    datasetForModel.add(dataPoints)
                    modelProducer.setEntries(datasetForModel)
                }
            }
            DashboardTab.LastSevenDays -> {
                CoroutineScope(Dispatchers.IO).launch {
                    dataPoints = viewModel.getLast7DaysDataPoints()

                    datasetForModel.add(dataPoints)
                    modelProducer.setEntries(datasetForModel)
                }
            }
            DashboardTab.LastThirtyDays -> {
                CoroutineScope(Dispatchers.IO).launch {
                    dataPoints = viewModel.getLast30DaysDataPoints()

                    datasetForModel.add(dataPoints)
                    modelProducer.setEntries(datasetForModel)
                }
            }
            DashboardTab.ThisMonth -> {
                CoroutineScope(Dispatchers.IO).launch {
                    dataPoints = viewModel.getThisMonthsDataPoints()

                    datasetForModel.add(dataPoints)
                    modelProducer.setEntries(datasetForModel)
                }
            }
        }
    }

    if(viewModel.isChartLoading){
        Column(
            modifier = Modifier.height(300.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LoadingAnimation()
        }
    }else{
        Column(
            modifier = Modifier
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (datasetForModel.isNotEmpty())
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp), elevation = 0.dp
                ) {
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
                                label = textComponent {
                                    this.color = AppColors.ButtonColorGradiant1.toArgb()
                                },
                                titleComponent = textComponent {
                                    this.color = AppColors.ButtonColorGradiant1.toArgb()
                                }
                            ),
                            bottomAxis = bottomAxis(
                                title = bottomLabel,
                                tickLength = 0.dp,
                                valueFormatter = { value, _ ->
                                    Utils.convertNumberToActualDate(value.toInt())
                                },
                                guideline = null,
                                label = textComponent {
                                    this.color = AppColors.ButtonColorGradiant1.toArgb()
                                    this.ellipsize = TextUtils.TruncateAt.MARQUEE
                                    this.lineCount = 1
                                    this.typeface = Typeface.SANS_SERIF
                                    this.margins = MutableDimensions(2f, 0f)
                                },
                                titleComponent = textComponent {
                                    this.color = AppColors.ButtonColorGradiant1.toArgb()
                                },
                                labelRotationDegrees = -30f,

                                )
                        )
                    }
                }

        }
    }



}
