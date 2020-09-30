package uz.triples.gdgdevfest.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TeamMembers(
    @PrimaryKey
    val id: Int,
    val teamName: String,
    val name: String?,
    val order: Int?,
    val photo: String?,
    val responsible: String?,
    var twitter: String?,
    var facebook: String?,
    var linkedIn: String?,
    var instagram: String?,
    var web: String?
)