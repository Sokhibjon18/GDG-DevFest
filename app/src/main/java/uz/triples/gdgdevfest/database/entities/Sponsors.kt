package uz.triples.gdgdevfest.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Sponsors(
    @PrimaryKey
    val id: Int,
    val title: String?,
    val name: String?,
    val logoUrl: String?,
    val order: Int?,
    val url: String?
)