package com.willbehn.notemanager.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
data class NoteNotFoundException(override val message: String): RuntimeException() {
}