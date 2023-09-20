package com.example.replysampleapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.replysampleapplication.R
import com.example.replysampleapplication.data.Email
import com.example.replysampleapplication.data.LocalEmailsDataProvider
import com.example.replysampleapplication.ui.theme.ReplySampleApplicationTheme

@Composable
fun ReplyEmailThreadItem(
    modifier: Modifier = Modifier,
    email: Email
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.medium)
            .padding(20.dp)

    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            ReplyProfileImage(
                picture = email.sender.avatar,
                description = email.sender.fullName
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = email.sender.firstName,
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = email.createdAt,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            IconButton(
                onClick = {},
                modifier = Modifier.clip(CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Favorite"
                )
            }
        }
        Text(
            text = email.subject,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
        )
        Text(
            text = email.body,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Button(
                onClick = { },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(id = R.string.reply))
            }
            Button(
                onClick = { },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(id = R.string.reply_all))
            }
        }
    }
}


@Preview
@Composable
fun ReplyEmailThreadItemPreview() {
    ReplySampleApplicationTheme {
        ReplyEmailThreadItem(
            email = LocalEmailsDataProvider.create()
        )
    }
}