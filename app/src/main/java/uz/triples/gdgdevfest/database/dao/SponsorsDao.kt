package uz.triples.gdgdevfest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.triples.gdgdevfest.database.entities.Speakers
import uz.triples.gdgdevfest.database.entities.Sponsors
import uz.triples.gdgdevfest.database.entities.Team
import uz.triples.gdgdevfest.database.entities.TeamMembers

@Dao
interface SponsorsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSponsors(sponsors: Sponsors)

    @Query("SELECT * From sponsors")
    fun getAllSponsor(): LiveData<List<Sponsors>>
}