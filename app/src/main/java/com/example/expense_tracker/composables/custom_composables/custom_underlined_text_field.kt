package com.example.expense_tracker.composables.custom_composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.expense_tracker.colors.AppColors
import com.example.expense_tracker.ui.theme.Expense_trackerTheme

@Composable
fun CustomUnderlinedTextField(
    value: String,
    hintText: String = "Enter Something",
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
    showPassword: Boolean = true,
    modifier: Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        TextField(
            value = value,
            placeholder = { CustomText(text = hintText, color = AppColors.HintTextColor) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            textStyle = TextStyle(
                color = AppColors.Black, fontSize = 16.sp, fontWeight = FontWeight.W500,
            ),
            visualTransformation = if (showPassword || keyboardType != KeyboardType.Password) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Divider(
            color = AppColors.DividerColor,
        )
    }


}

@Preview(showBackground = true)
@Composable
fun CustomInputTextFieldPreview() {
    Expense_trackerTheme {
        CustomUnderlinedTextField(
            value = "Username",
            onValueChange = {},
            modifier = Modifier,
        )
    }
}