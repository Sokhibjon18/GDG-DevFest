package uz.triples.gdgdevfest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.triples.gdgdevfest.database.dao.AgendaDao
import uz.triples.gdgdevfest.database.dao.SessionsDao
import uz.triples.gdgdevfest.database.entities.Agenda
import uz.triples.gdgdevfest.database.entities.Sessions

@Database(entities = [Agenda::class, Sessions::class], version = 1)
abstract class GDGTashkentDatabase : RoomDatabase() {

    abstract fun agendaDao(): AgendaDao
    abstract fun sessionsDao(): SessionsDao

    companion object {
        private var instance: GDGTashkentDatabase? = null

        fun getInstance(context: Context): GDGTashkentDatabase? {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context, GDGTashkentDatabase::class.java, "GDGTashkent.db")
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return instance
        }
    }
}