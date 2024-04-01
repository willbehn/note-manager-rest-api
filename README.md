# note-manager-rest-api

## About: 
This is a Kotlin and springboot based REST api for managing notes with basic CRUD functionality, currently work in progress. 

### API documentation

| HTTP Method | Endpoint            | Description                             | Request Body                             | Response Body                   |
|-------------|---------------------|-----------------------------------------|------------------------------------------|--------------------------------|
| GET         | /api/notes          | Get all notes                           | None                                     | List of NoteDto objects        |
| GET         | /api/note/{id}      | Get a note by ID                        | None                                     | NoteDto object                 |
| GET         | /api/note/{title}   | Get notes by title                      | None                                     | List of NoteDto objects        |
| POST        | /api/create         | Create a new note                       | NoteCreateRequest object                | NoteDto object                 |
| PATCH       | /api/update/{id}    | Update an existing note                 | NoteUpdateRequest object                | NoteDto object                 |
| DELETE      | /api/delete/{id}    | Delete a note by ID                     | None                                     | Success message                |

**NoteCreateRequest and NoteUpdateRquest example body**
```json
{
  "title": "To-do list",
  "content": "1: Handle mat 2: Vaske leilighet",
  "createdOn": "2024-03-30T14:06:26.583"
}
```

**NoteDto object response example**
```json
{
  "id": 1,
  "title": "Handleliste",
  "content": "Melk, egg, pizza, ris, yoghurt",
  "createdOn": "2024-03-30T14:06:26.583"
}
```

