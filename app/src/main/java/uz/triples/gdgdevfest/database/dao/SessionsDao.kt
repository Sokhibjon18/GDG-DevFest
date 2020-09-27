package uz.triples.gdgdevfest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.triples.gdgdevfest.database.entities.Agenda
import uz.triples.gdgdevfest.database.entities.Sessions

@Dao
interface SessionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSessions(sessions: Sessions)

    @Query("SELECT * From sessions")
    fun getAllAgenda(): LiveData<List<Sessions>>
}