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
    fun getNoteById(@PathVariable id: Long): ResponseEntity<NoteDto> =
        ResponseEntity(service.getNoteById(id), HttpStatus.OK)

    @GetMapping("note/{title}")
    fun getNotseByTitle(@PathVariable title: String): ResponseEntity<List<NoteDto>> =
        ResponseEntity(service.getNotesByTitle(title), HttpStatus.OK)

    // POST endepunkt (legge inn stuff)
    @PostMapping("create")
    fun createNote(
        @Valid @RequestBody createRequest: NoteCreateRequest
    ): ResponseEntity<NoteDto> = ResponseEntity(service.createNote(createRequest), HttpStatus.OK)

    @PatchMapping("update/{id}")
    fun updateNote(
        @PathVariable id: Long,
        @Valid @RequestBody updateRequest: NoteUpdateRequest
    ): ResponseEntity<NoteDto> = ResponseEntity(service.updateNote(id, updateRequest), HttpStatus.OK)

    @DeleteMapping("delete/{id}")
    fun deleteNote(
        @PathVariable id: Long
    ): ResponseEntity<String> = ResponseEntity(service.deleteNote(id), HttpStatus.OK)
}