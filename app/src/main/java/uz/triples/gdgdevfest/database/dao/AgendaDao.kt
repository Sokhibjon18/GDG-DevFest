package uz.triples.gdgdevfest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.triples.gdgdevfest.database.entities.Agenda

@Dao
interface AgendaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAgenda(agenda: Agenda)

    @Query("SELECT * From agenda")
    fun getAllAgenda(): LiveData<List<Agenda>>
}