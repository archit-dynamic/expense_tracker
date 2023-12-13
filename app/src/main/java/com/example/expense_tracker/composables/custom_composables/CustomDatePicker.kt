package com.example.expense_tracker.composables.custom_composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expense_tracker.colors.AppColors
import com.example.expense_tracker.strings.AppImages
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.*
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    label: String? = null,
    onSelect: (LocalDate) -> Unit,
) {

    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("MMM dd yyyy").format(pickedDate)
        }
    }

    val dateDialogState = rememberMaterialDialogState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        if (label != null)
            CustomText(
                text = label,
                fontSize = 15.sp,
                fontWeight = FontWeight.W500,
                color = AppColors.LabelColor
            )
        if (label != null)
            VerticalSpace(height = 4.dp)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    color = AppColors.BackCircle,
                    width = 1.dp,
                    shape = RoundedCornerShape(16.dp)
                )
                .clickable {
                    dateDialogState.show()
                }
                .padding(horizontal = 12.dp, vertical = 16.dp), contentAlignment = Alignment.Center
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomText(text = formattedDate, fontSize = 16.sp, color = AppColors.Black)
                Image(
                    painter = painterResource(id = AppImages.icDatePicker),
                    contentDescription = ""
                )
            }
        }
        MaterialDialog(
            dialogState = dateDialogState,
            buttons = {
                positiveButton(text = "Ok")
                negativeButton(text = "Cancel")
            }
        ) {
            datepicker(
                initialDate = if (pickedDate != LocalDate.now()) pickedDate else LocalDate.now(),
                title = "Pick a date",
                allowedDateValidator = {
                    it <= LocalDate.now()
                },
                colors = DatePickerDefaults.colors(
                    dateActiveBackgroundColor = AppColors.ButtonColorGradiant1,
                    dateActiveTextColor = AppColors.White,
                    dateInactiveBackgroundColor = AppColors.White,
                    dateInactiveTextColor = AppColors.ButtonColorGradiant2
                ),
            ) {
                pickedDate = it

//                onSelect(it.toEpochSecond(LocalTime.now(),ZoneOffset.UTC).toString())
                onSelect(it)
            }
        }
    }

}