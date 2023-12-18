package com.example.expense_tracker.composables.custom_composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.expense_tracker.colors.AppColors

@Composable
fun CustomAvatar(
    url: String? = null,
    image: Int,
    radius: Float,
    borderColor: Color = AppColors.Black,
) {

    AsyncImage(
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(size = (radius * 2).dp)
            .clip(shape = CircleShape)
            .border(width = 1.dp, color = borderColor, shape = CircleShape),
        model = url,
        placeholder = painterResource(id = image),
        error = painterResource(
            id = image
        ),
        contentDescription = "Avatar Image",
    )

}

@Composable
fun CustomAvatarForInitials(
    initials: String,
    backgroundColor: Color = AppColors.ButtonColorGradiant2,
    textColor: Color = AppColors.White,
    fontSize: TextUnit = 24.sp,
    radius: Float
) {

    Box(
        modifier = Modifier.background(color = backgroundColor, shape = CircleShape).size(size = (radius * 2).dp),
        contentAlignment = Alignment.Center
    ) {
        CustomText(
            modifier = Modifier.padding(4.dp),
            text = initials,
            color = textColor,
            fontSize = fontSize,
            fontWeight = FontWeight.SemiBold
        )
    }

}