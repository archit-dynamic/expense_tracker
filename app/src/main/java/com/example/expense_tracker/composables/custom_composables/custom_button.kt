package com.example.expense_tracker.composables.custom_composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expense_tracker.R
import com.example.expense_tracker.ui.theme.ButtonColorGradiant1
import com.example.expense_tracker.ui.theme.Expense_trackerTheme
import com.example.expense_tracker.ui.theme.White

@Composable
fun CustomButton(
    text: String,
    suffixIcon: Int? = null,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(1000.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ButtonColorGradiant1,
        ),
        onClick = onClick
    ) {
        Text(
            text = text,
            color = White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .weight(1f),
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
        )
        if(suffixIcon != null) Image(painter = painterResource(id = suffixIcon), contentDescription = "suffixIcon")
    }
}

@Preview(showBackground = true)
@Composable
fun CustomButtonPreview() {
    Expense_trackerTheme {
        CustomButton(
            text = "Click Me",
            suffixIcon = R.drawable.ic_keyboard_right_white,
            onClick = {},
        )
    }
}