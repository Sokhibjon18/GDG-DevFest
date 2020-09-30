package uz.triples.gdgdevfest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.triples.gdgdevfest.database.dao.*
import uz.triples.gdgdevfest.database.entities.*

@Database(
    entities = [Agenda::class, Sessions::class, Speakers::class, Team::class, TeamMembers::class, Sponsors::class, FAQ::class],
    version = 1
)
abstract class GDGTashkentDatabase : RoomDatabase() {

    abstract fun agendaDao(): AgendaDao
    abstract fun sessionsDao(): SessionsDao
    abstract fun speakersDao(): SpeakersDao
    abstract fun teamMembersDao(): TeamMembersDao
    abstract fun teamDao(): TeamDao
    abstract fun sponsorsDao(): SponsorsDao
    abstract fun faqDao(): FAQDao

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