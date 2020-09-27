package uz.triples.gdgdevfest.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import uz.triples.gdgdevfest.database.entities.Agenda
import uz.triples.gdgdevfest.database.entities.Sessions
import uz.triples.gdgdevfest.repositories.AgendaRepository

class AgendaViewModel(application: Application): AndroidViewModel(application) {

    private val repository = AgendaRepository(application)

    fun getAgenda(): LiveData<List<Agenda>> {
        return repository.getAgendaList()
    }

    suspend fun getSession(session: Int): Sessions{
        return repository.getSession(session)
    }
}