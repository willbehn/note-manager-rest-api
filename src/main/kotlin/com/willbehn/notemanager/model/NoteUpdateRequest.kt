package com.willbehn.notemanager.model

import java.time.LocalDateTime

data class NoteUpdateRequest(
    val title: String?,
    val content: String?,
    val createdOn: LocalDateTime?
)
