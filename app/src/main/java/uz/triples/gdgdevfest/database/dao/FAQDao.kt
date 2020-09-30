package uz.triples.gdgdevfest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.triples.gdgdevfest.database.entities.FAQ

@Dao
interface FAQDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFAQ(faq: FAQ)

    @Query("SELECT * From faq")
    fun getAllFAQ(): LiveData<List<FAQ>>
}