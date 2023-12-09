package com.example.expense_tracker.composables.custom_composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.expense_tracker.R
import com.example.expense_tracker.ui.theme.DividerColor
import com.example.expense_tracker.ui.theme.Expense_trackerTheme
import com.example.expense_tracker.ui.theme.HintTextColor
import com.example.expense_tracker.ui.theme.TextFieldIconColor

@Composable
fun CustomInputTextField(
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
            placeholder = { Text(text = hintText, color = HintTextColor) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            textStyle = TextStyle(
                color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.W500,
            ),
            visualTransformation = if (showPassword || keyboardType != KeyboardType.Password) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Divider(
            color = DividerColor,
        )
    }


}

@Preview(showBackground = true)
@Composable
fun CustomInputTextFieldPreview() {
    Expense_trackerTheme {
        CustomInputTextField(
            value = "Username",
            onValueChange = {},
            modifier = Modifier,
        )
    }
}