package com.willbehn.notemanager.service


import com.willbehn.notemanager.data.Note
import com.willbehn.notemanager.exception.BadRequestException
import com.willbehn.notemanager.exception.NoteNotFoundException
import com.willbehn.notemanager.model.NoteCreateRequest
import com.willbehn.notemanager.model.NoteDto
import com.willbehn.notemanager.model.NoteUpdateRequest
import com.willbehn.notemanager.repository.NoteRepository
import org.springframework.stereotype.Service
import org.springframework.util.ReflectionUtils
import java.lang.reflect.Field
import java.util.stream.Collectors
import kotlin.reflect.full.memberProperties

@Service
class NoteService(private val repository: NoteRepository) {
    private fun convertEntityToDto(note: Note): NoteDto{
        return NoteDto(
            note.id,
            note.title,
            note.content,
            note.createdOn
        )
    }

    private fun assignValuesToEntity(note: Note, noteRequest: NoteCreateRequest){
        note.title = noteRequest.title
        note.content = noteRequest.content
        note.createdOn = noteRequest.createdOn
    }

    private fun checkForNoteId(id: Long){
        if (!repository.existsById(id)) throw NoteNotFoundException("Note with id $id does not exist")
    }

    fun getAllNotes(): List<NoteDto> =
        repository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList())

    fun getNoteById(id: Long): NoteDto {
        checkForNoteId(id)
        return convertEntityToDto(repository.findNoteById(id))
    }


    fun getNotesByTitle(title: String): List<NoteDto> {
        //Trenger en exception her for at tittel ikke finnes

        return repository.queryNotesByTitle(title).stream().map(this::convertEntityToDto).collect((Collectors.toList()))

    }

    fun createNote(createRequest: NoteCreateRequest): NoteDto{
        if (repository.doesTitleExist(createRequest.title)) throw BadRequestException("Note allready exists")

        val note = Note()
        assignValuesToEntity(note,createRequest)

        val savedNote = repository.save(note)

        return convertEntityToDto(savedNote)
    }

    fun updateNote(id: Long, updateRequest: NoteUpdateRequest): NoteDto {
        checkForNoteId(id)

        val existingNote = repository.findNoteById(id)

        for (prop in NoteUpdateRequest::class.memberProperties){
            if (prop.get(updateRequest) != null){
                val field: Field? = ReflectionUtils.findField(Note::class.java, prop.name)
                field?.let {
                    it.isAccessible = true
                    ReflectionUtils.setField(it, existingNote, prop.get(updateRequest))
                }
            }
        }

        val savedNote: Note = repository.save(existingNote)

        return convertEntityToDto(savedNote)
    }

    fun deleteNote(id: Long): String{
        checkForNoteId(id)
        repository.deleteById(id)
        return "Note with id $id deleted"
    }

}