package com.willbehn.notemanager.repository

import com.willbehn.notemanager.data.Note
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface NoteRepository: JpaRepository<Note, Long> {
    fun findNoteById(id: Long): Note

    @Query( value = "SELECT * FROM notes n WHERE n.title LIKE %:title%", nativeQuery = true)
    fun queryNotesByTitle(@Param("title") title: String): List<Note>

    @Query(value = "SELECT CASE WHEN COUNT(n) > 0 THEN TRUE ELSE FALSE END FROM Note n WHERE n.title = ?1")
    fun doesTitleExist(title: String): Boolean

}