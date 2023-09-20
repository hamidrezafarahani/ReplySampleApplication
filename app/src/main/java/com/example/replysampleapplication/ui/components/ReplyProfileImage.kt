package com.example.replysampleapplication.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.replysampleapplication.R
import com.example.replysampleapplication.ui.theme.ReplySampleApplicationTheme

@Composable
fun ReplyProfileImage(
    @DrawableRes picture: Int,
    description: String,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = picture),
        contentDescription = description,
        modifier = modifier.size(40.dp).clip(CircleShape)
    )
}


@Preview
@Composable
fun ReplyProfileImagePreview() {
    ReplySampleApplicationTheme {
        ReplyProfileImage(
            R.drawable.avatar_0, "profile pic"
        )
    }
}