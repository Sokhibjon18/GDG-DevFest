package uz.triples.gdgdevfest.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Agenda(
    @PrimaryKey
    val id: Int,
    val date: String?,
    val endTime: String?,
    val startTime: String?,
    val sessionsList: String?
) {
}