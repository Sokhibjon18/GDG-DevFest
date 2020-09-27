package uz.triples.gdgdevfest.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.triples.gdgdevfest.database.GDGTashkentDatabase
import uz.triples.gdgdevfest.database.entities.Agenda
import uz.triples.gdgdevfest.database.entities.Sessions

class AgendaRepository(application: Application) {

    private val database = GDGTashkentDatabase.getInstance(application)
    private var agendaLiveData: LiveData<List<Agenda>>

    init {
        agendaLiveData = database!!.agendaDao().getAllAgenda()
    }

    fun getAgendaList(): LiveData<List<Agenda>>{
        return agendaLiveData
    }

    suspend fun getSession(session: Int): Sessions {
        return database!!.sessionsDao().getSession(session)
    }
}