package uz.triples.gdgdevfest.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Speakers(
    @PrimaryKey
    val id: String,
    val name: String?,
    val shortBio: String?,
    val bio: String?,
    val company: String?,
    val companyLogo: String?,
    val country: String?,
    val photo: String?,
    val title: String?,
    var twitter: String?,
    var facebook: String?,
    var linkedIn: String?,
    var instagram: String?,
    var web: String?
): Serializable
