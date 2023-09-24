package com.example.replysampleapplication.ui

import androidx.lifecycle.ViewModel
import com.example.replysampleapplication.data.Email
import com.example.replysampleapplication.data.LocalEmailsDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ReplyHomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ReplyHomeUIState(loading = true))
    val uiState: StateFlow<ReplyHomeUIState> = _uiState

    init {
        val allEmails = LocalEmailsDataProvider.allEmails
        _uiState.value = ReplyHomeUIState(
            emails = allEmails,
            loading = false
        )
    }

    fun setSelectedEmail(emailId: Long) {
        val email = uiState.value.emails.find { it.id == emailId }
        _uiState.value = _uiState.value.copy(
            selectedEmail = email,
            isDetailOnlyOpen = true
        )
    }


    fun closeDetailScreen() {
        _uiState.value = uiState.value.copy(
            selectedEmail = uiState.value.emails.first(),
            isDetailOnlyOpen = false
        )
    }
}

data class ReplyHomeUIState(
    val emails: List<Email> = emptyList(),
    val selectedEmail: Email? = null,
    val isDetailOnlyOpen: Boolean = false,
    val loading: Boolean = false,
    val error: String? = null
)