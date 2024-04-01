package com.willbehn.notemanager.data

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

@Entity
@Table(name = "notes")
class Note {
    @Id
    @GeneratedValue(generator = "notesSequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "noteSequence", sequenceName = "noteSequence", allocationSize = 1)
    val id: Long = 0

    @NotBlank
    @Column(name = "title", nullable = false, unique = false) // Kanskje sett uniqe til true?
    var title: String = ""

    @Column(name = "content", nullable = false)
    var content: String = ""

    @Column(name = "created_on", nullable = false)
    var createdOn: LocalDateTime = LocalDateTime.now()

}