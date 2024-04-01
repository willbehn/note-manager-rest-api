package com.willbehn.notemanager.model

import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

data class NoteCreateRequest(
    @NotBlank(message = "The title cant be empty")
    val title: String,
    val content: String,
    val createdOn: LocalDateTime
)
