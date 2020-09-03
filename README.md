# Journal Application
A command-line interface journal application using Java 11

## Features
- [x] User can enter a new journal entry
- [x] User can store journal entries
    - [x] Store entries locally to txt file
    - [x] Store entries in a postgresSQL database
- [x] User can search for existing entries
    - [x] Return entries from a given time window
    - [x] Return entries containing a keyword
- [x] User can editing an existing entry
- [x] Documentation
- [x] Unit tests
- [x] Logging

## Tech Stack
- Java 11:
    - File I/O
    - Collections
- Maven 3
- JUnit 4.11
- Git
- PostgresSql 12

### To Set Up Docker Container
- Make sure your in folder: /JournalApp/DB
- `$ docker build -t journalapp .`
- `$ docker run --rm -p 5432:5432 journalapp`