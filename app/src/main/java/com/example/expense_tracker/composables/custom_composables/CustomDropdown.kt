package com.example.expense_tracker.composables.custom_composables

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expense_tracker.colors.AppColors

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

                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        expanded.value = !expanded.value
                    },
                trailingIcon = {
                    Icon(icon, contentDescription = "dropdown arrow", modifier = Modifier.clickable {
                        expanded.value = !expanded.value
                    })
                }, readOnly = true,
            )
            /*Surface(
                modifier = Modifier.fillMaxWidth().background(color = Color.Transparent).clickable {
                    expanded.value = !expanded.value
                }
            ){

            }*/
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = {
                expanded.value = false
            },
            modifier = Modifier.fillMaxWidth(),
            content = {
                list.forEach { label: String ->
                    DropdownMenuItem(onClick = {
                        selectedItem.value = label
                        expanded.value = false
                        onSelect(label)
                    }) {
                        CustomText(text = label)
                    }
                }
            }
        )


    }

}
