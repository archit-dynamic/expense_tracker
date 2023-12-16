package com.example.expense_tracker.composables.custom_composables

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.example.expense_tracker.colors.AppColors
import com.example.expense_tracker.composables.CustomDropdownOptionItem
import com.example.expense_tracker.utils.Utils

@SuppressLint("NewApi")
@Composable
fun CustomDropdown(
    label: String? = null,
    list: List<String>,
    onSelect: (String)->Unit
) {

    val expanded = remember {
        mutableStateOf(false)
    }
    val selectedItem = remember {
        mutableStateOf("")
    }
    val icon = if (expanded.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    val keyboardController = LocalSoftwareKeyboardController.current
    val configuration = LocalConfiguration.current

    Column {
        if (label != null)
            CustomText(
                text = label,
                fontSize = 15.sp,
                fontWeight = FontWeight.W500,
                color = AppColors.LabelColor
            )
        if (label != null)
            VerticalSpace(height = 4.dp)
        Box(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = selectedItem.value,
                onValueChange = {
                    selectedItem.value = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,

                ), visualTransformation = VisualTransformation.None,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        expanded.value = !expanded.value
                        keyboardController?.hide()
                    }.border(
                        width = 1.dp,
                        color = AppColors.BackCircle,
                        shape = RoundedCornerShape(16.dp)
                    ),
                trailingIcon = {
                    Icon(icon, contentDescription = "dropdown arrow", modifier = Modifier.clickable {
                        expanded.value = !expanded.value
                        keyboardController?.hide()
                    })
                }, readOnly = true,
            )
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = {
                expanded.value = false
            },properties = PopupProperties(focusable = false),
            offset = DpOffset(x = 0.dp, y = 6.dp),
            modifier = Modifier.width((configuration.screenWidthDp - 40).dp),
            content = {
                list.forEach { label: String ->
                    DropdownMenuItem(onClick = {
                        selectedItem.value = label
                        expanded.value = false
                        onSelect(label)
                    }) {
                        CustomDropdownOptionItem(
                            categoryImage = Utils.getCategoryImage(label),
                            categoryName = label
                        )
                    }
                }
            }
        )


    }

}
