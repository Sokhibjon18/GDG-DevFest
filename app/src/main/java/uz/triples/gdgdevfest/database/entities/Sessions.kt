package uz.triples.gdgdevfest.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Sessions(
    @PrimaryKey
    val session: Int,
    val sessionModel: String
) {
}