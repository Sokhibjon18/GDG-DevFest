package uz.triples.gdgdevfest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.triples.gdgdevfest.database.entities.Speakers

@Dao
interface SpeakersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpeakers(speakers: Speakers)

    @Query("SELECT * From speakers")
    fun getAllSpeakers(): LiveData<List<Speakers>>

    @Query("SELECT * From speakers where id = :name")
    fun getSpeaker(name: String): Speakers

    @Query("SELECT * From speakers where name = :name")
    fun getSpeakerByName(name: String): Speakers
}