package com.willbehn.notemanager.controller

import com.willbehn.notemanager.model.NoteCreateRequest
import com.willbehn.notemanager.model.NoteDto
import com.willbehn.notemanager.model.NoteUpdateRequest
import com.willbehn.notemanager.service.NoteService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class NoteController(private val service: NoteService) {
    // Get endepunkt (hente stuff)
    @GetMapping("notes")
    fun getAllNotes(): ResponseEntity<List<NoteDto>> = ResponseEntity(service.getAllNotes(), HttpStatus.OK)

    @GetMapping("note/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<NoteDto> =
        ResponseEntity(service.getNoteById(id), HttpStatus.OK)

    // POST endepunkt (legge inn stuff)
    @PostMapping("create")
    fun createTask(
        @Valid @RequestBody createRequest: NoteCreateRequest
    ): ResponseEntity<NoteDto> = ResponseEntity(service.createNote(createRequest), HttpStatus.OK)

    @PatchMapping("update/{id}")
    fun updateTask(
        @PathVariable id: Long,
        @Valid @RequestBody updateRequest: NoteUpdateRequest
    ): ResponseEntity<NoteDto> = ResponseEntity(service.updateNote(id, updateRequest), HttpStatus.OK)

    @DeleteMapping("delete/{id}")
    fun deleteTask(
        @PathVariable id: Long
    ): ResponseEntity<String> = ResponseEntity(service.deleteNote(id), HttpStatus.OK)
}