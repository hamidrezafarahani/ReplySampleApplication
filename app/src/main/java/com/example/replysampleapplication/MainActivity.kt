package com.example.replysampleapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.replysampleapplication.ui.ReplyApp
import com.example.replysampleapplication.ui.ReplyHomeViewModel
import com.example.replysampleapplication.ui.theme.ReplySampleApplicationTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ReplyHomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val replyUiState by viewModel.uiState.collectAsState()

            ReplySampleApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ReplyApp(
                        replyHomeUIState = replyUiState,
                        closeDetailScreen = { viewModel.closeDetailScreen() },
                        navigateToDetail = {
                            viewModel.setSelectedEmail(it)
                        }
                    )
                }
            }
        }
    }
}