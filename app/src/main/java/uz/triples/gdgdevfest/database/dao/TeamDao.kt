package uz.triples.gdgdevfest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.triples.gdgdevfest.database.entities.Speakers
import uz.triples.gdgdevfest.database.entities.Team
import uz.triples.gdgdevfest.database.entities.TeamMembers

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeam(team: Team)

    @Query("SELECT * From team")
    fun getAllTeam(): LiveData<List<Team>>
}