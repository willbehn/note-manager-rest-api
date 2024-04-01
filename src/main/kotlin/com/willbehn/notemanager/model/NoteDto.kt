package com.willbehn.notemanager.model

import java.time.LocalDateTime

data class NoteDto (
    val id: Long,
    val title: String,
    val content: String,
    val createdOn: LocalDateTime
)