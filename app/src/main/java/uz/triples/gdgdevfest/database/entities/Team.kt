package uz.triples.gdgdevfest.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Team(
    @PrimaryKey
    val name: String,
    val membersCount: Int?
)