package com.example.replysampleapplication.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.replysampleapplication.R
import com.example.replysampleapplication.data.Email
import com.example.replysampleapplication.data.LocalEmailsDataProvider
import com.example.replysampleapplication.ui.components.EmailDetailAppBar
import com.example.replysampleapplication.ui.components.ReplyEmailListItem
import com.example.replysampleapplication.ui.components.ReplyEmailThreadItem
import com.example.replysampleapplication.ui.components.ReplySearchBar
import com.example.replysampleapplication.ui.theme.ReplySampleApplicationTheme


@Composable
fun ReplyEmailDetail(
    modifier: Modifier = Modifier,
    email: Email,
    isFullScreen: Boolean = true,
    onBackPressed: () -> Unit
) {
    LazyColumn(modifier = modifier.padding(top = 16.dp)) {
        item {
            EmailDetailAppBar(email = email, isFullScreen = isFullScreen) {
                onBackPressed()
            }
        }
        items(items = email.threads, key = { it.id }) {
            ReplyEmailThreadItem(email = it)
        }
    }
}

@Preview
@Composable
fun ReplyEmailDetailPreview() {
    ReplySampleApplicationTheme {
        ReplyEmailDetail(
            email = LocalEmailsDataProvider.allEmails[0]
        ) {}
    }
}


@Composable
fun ReplyEmailList(
    modifier: Modifier = Modifier,
    emails: List<Email>,
    emailLazyListState: LazyListState,
    selectedEmail: Email? = null,
    navigateToDetail: (Long) -> Unit
) {
    LazyColumn(modifier = modifier, state = emailLazyListState) {
        item {
            ReplySearchBar(modifier = Modifier.fillMaxWidth())
        }
        items(items = emails, key = { it.id }) {
            ReplyEmailListItem(
                email = it,
                isSelected = it.id == selectedEmail?.id,
                navigateToDetail = navigateToDetail
            )
        }
    }
}

@Preview
@Composable
fun ReplyEmailListPreview() {
    ReplySampleApplicationTheme {
        ReplyEmailList(
            emails = LocalEmailsDataProvider.allEmails,
            emailLazyListState = rememberLazyListState(),
        ) {}
    }
}


@Composable
fun ReplyEmailListContent(
    modifier: Modifier = Modifier,
    replyHomeUIState: ReplyHomeUIState,
    emailLazyListState: LazyListState,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long) -> Unit
) {
    if (replyHomeUIState.selectedEmail != null && replyHomeUIState.isDetailOnlyOpen) {
        BackHandler {
            closeDetailScreen()
        }
        ReplyEmailDetail(email = replyHomeUIState.selectedEmail) {
            closeDetailScreen()
        }
    } else {
        ReplyEmailList(
            modifier = modifier,
            emails = replyHomeUIState.emails,
            emailLazyListState = emailLazyListState,
            navigateToDetail = navigateToDetail
        )
    }
}

@Preview
@Composable
fun ReplyEmailListContentPreview() {

    val viewModel = ReplyHomeViewModel()
    val replyUiState = viewModel.uiState.collectAsState().value

    ReplySampleApplicationTheme {
        ReplyEmailListContent(
            replyHomeUIState = replyUiState,
            emailLazyListState = rememberLazyListState(),
            closeDetailScreen = {},
            navigateToDetail = {}
        )
    }
}


@Composable
fun ReplyInboxScreen(
    modifier: Modifier = Modifier,
    replyHomeUIState: ReplyHomeUIState,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long) -> Unit
) {

    val emailLazyListState = rememberLazyListState()

    Box(modifier = modifier.fillMaxSize()) {
        ReplyEmailListContent(
            replyHomeUIState = replyHomeUIState,
            emailLazyListState = emailLazyListState,
            closeDetailScreen = closeDetailScreen,
            navigateToDetail = navigateToDetail
        )

        LargeFloatingActionButton(
            onClick = {},
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = stringResource(id = R.string.edit),
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Preview
@Composable
fun ReplyInboxScreenPreview() {

    val viewModel = ReplyHomeViewModel()
    val replyUiState = viewModel.uiState.collectAsState().value

    ReplySampleApplicationTheme {
        ReplyInboxScreen(
            replyHomeUIState = replyUiState,
            closeDetailScreen = {},
            navigateToDetail = {}
        )
    }
}