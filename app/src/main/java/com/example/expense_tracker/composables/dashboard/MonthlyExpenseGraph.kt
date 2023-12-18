package com.example.expense_tracker.composables.dashboard

import android.graphics.Typeface
import android.text.TextUtils
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.example.expense_tracker.animations.LoadingAnimation
import com.example.expense_tracker.colors.AppColors
import com.example.expense_tracker.composables.custom_composables.VerticalSpace
import com.example.expense_tracker.enum.DashboardTab
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
import java.util.Calendar
import java.util.Date

@Composable
fun MonthlyExpenseGraph(
    bottomLabel: String,
    viewModel: DashboardViewModel,
    tab: DashboardTab = DashboardTab.Today
) {

    val showValueCard = remember {
        mutableStateOf(false)
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
                showValueCard.value = true
                CoroutineScope(Dispatchers.IO).launch {
                    dataPoints = viewModel.getTodayDataPoints().first
                    viewModel.expenseData = viewModel.getTodayDataPoints().second
                    Log.d("viewModel.expenseData","${viewModel.expenseData.size}")
                    datasetForModel.add(dataPoints)
                    modelProducer.setEntries(datasetForModel)
                }
            }
            DashboardTab.LastSevenDays -> {
                showValueCard.value = false
                CoroutineScope(Dispatchers.IO).launch {
                    dataPoints = viewModel.getLast7DaysDataPoints().first
                    viewModel.expenseData = viewModel.getLast7DaysDataPoints().second
                    Log.d("viewModel.expenseData","${viewModel.expenseData.size}")
                    datasetForModel.add(dataPoints)
                    modelProducer.setEntries(datasetForModel)
                }
            }
            DashboardTab.LastThirtyDays -> {
                showValueCard.value = false
                CoroutineScope(Dispatchers.IO).launch {
                    dataPoints = viewModel.getLast30DaysDataPoints().first
                    viewModel.expenseData = viewModel.getLast30DaysDataPoints().second
                    Log.d("viewModel.expenseData","${viewModel.expenseData.size}")
                    datasetForModel.add(dataPoints)
                    modelProducer.setEntries(datasetForModel)
                }
            }
            DashboardTab.ThisMonth -> {
                showValueCard.value = false
                CoroutineScope(Dispatchers.IO).launch {
                    dataPoints = viewModel.getThisMonthsDataPoints().first
                    viewModel.expenseData = viewModel.getThisMonthsDataPoints().second
                    Log.d("viewModel.expenseData","${viewModel.expenseData.size}")
                    datasetForModel.add(dataPoints)
                    modelProducer.setEntries(datasetForModel)
                }
            }
        }
    }

    if(viewModel.isChartLoading && !showValueCard.value){
        Column(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LoadingAnimation()
        }
    } else if(showValueCard.value){
        VerticalSpace(height = 0.dp)
    }else {
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
                                    /*var number= 0
                                    if(viewModel.selectedTab == DashboardTab.LastSevenDays){
                                        number = 7 - value.toInt()
                                    }else if(viewModel.selectedTab == DashboardTab.LastThirtyDays){
                                        number = 30 - value.toInt()
                                    }else if(viewModel.selectedTab == DashboardTab.ThisMonth){
                                        number = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - value.toInt()
                                    }*/
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
