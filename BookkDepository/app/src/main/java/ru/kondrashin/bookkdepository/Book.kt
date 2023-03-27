package ru.kondrashin.bookkdepository

import java.util.*

class Book {
    var id: UUID
        private set
    var title = ""
    var date: Date
    var isReaded = false
    //Генерирование уникального идентификатора и даты
    init {
        id = UUID.randomUUID()
        date = Date()
    }
}