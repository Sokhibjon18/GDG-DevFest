package uz.triples.gdgdevfest.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FAQ (
    @PrimaryKey
    val id: Int,
    val question: String?,
    val answer: String?
)