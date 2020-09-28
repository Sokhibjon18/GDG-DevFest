package uz.triples.gdgdevfest.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import uz.triples.gdgdevfest.database.GDGTashkentDatabase
import uz.triples.gdgdevfest.database.entities.Agenda
import uz.triples.gdgdevfest.database.entities.Sessions
import uz.triples.gdgdevfest.database.entities.Speakers

class AgendaRepository(application: Application) {

    private val database = GDGTashkentDatabase.getInstance(application)
    private var agendaLiveData: LiveData<List<Agenda>>

    init {
        agendaLiveData = database!!.agendaDao().getAllAgenda()
    }

    fun getAgendaList(): LiveData<List<Agenda>> {
        return agendaLiveData
    }

    fun getSession(session: Int): Sessions? {
        return database!!.sessionsDao().getSession(session)
    }

    fun getSpeaker(speaker: String): Speakers? {
        return database!!.speakersDao().getSpeaker(speaker)
    }

    fun getSpeakerByName(name: String): Speakers? {
        return database!!.speakersDao().getSpeakerByName(name)
    }
}