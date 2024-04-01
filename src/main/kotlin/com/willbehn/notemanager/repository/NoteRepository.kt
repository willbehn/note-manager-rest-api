package com.willbehn.notemanager.repository

import com.willbehn.notemanager.data.Note
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface NoteRepository: JpaRepository<Note, Long> {
    fun findNoteById(id: Long): Note

    /*@Query( value = "SELECT * FROM notes n WHERE n.title LIKE %:title%")
    fun queryNoteByTitle(@Param("title") title: String): Note*/

    /*@Query("SELECT CASE WHEN COUNT(t) > 0 THEN TRUE ELSE FALSE END FROM notes n WHERE n.title = ?1")
    fun doesTitleExist(title: String): Boolean*/

}