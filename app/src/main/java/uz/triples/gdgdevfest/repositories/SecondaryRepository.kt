package uz.triples.gdgdevfest.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import uz.triples.gdgdevfest.database.GDGTashkentDatabase
import uz.triples.gdgdevfest.database.entities.*

class SecondaryRepository(application: Application) {

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

    fun getSpeakers(): LiveData<List<Speakers>> {
        return database!!.speakersDao().getAllSpeakers()
    }

    fun getSpeaker(speaker: String): Speakers? {
        return database!!.speakersDao().getSpeaker(speaker)
    }

    fun getSpeakerByName(name: String): Speakers? {
        return database!!.speakersDao().getSpeakerByName(name)
    }

    fun getTeamMembers(teamName: String?): List<TeamMembers> {
        return database!!.teamMembersDao().getAllTeamMembers(teamName)
    }

    fun getTeams(): LiveData<List<Team>> {
        return database!!.teamDao().getAllTeam()
    }

    fun getSponsors(): LiveData<List<Sponsors>> {
        return  database!!.sponsorsDao().getAllSponsor()
    }

    fun getFAQ(): LiveData<List<FAQ>> {
        return database!!.faqDao().getAllFAQ()
    }
}