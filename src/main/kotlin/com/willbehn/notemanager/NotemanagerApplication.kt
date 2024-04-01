package com.willbehn.notemanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NotemanagerApplication

fun main(args: Array<String>) {
	runApplication<NotemanagerApplication>(*args)
}
