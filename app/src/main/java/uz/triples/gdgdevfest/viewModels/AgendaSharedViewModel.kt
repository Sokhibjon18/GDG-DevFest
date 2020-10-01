package uz.triples.gdgdevfest.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.triples.gdgdevfest.database.entities.Agenda
import uz.triples.gdgdevfest.database.entities.Sessions
import uz.triples.gdgdevfest.database.entities.Speakers
import uz.triples.gdgdevfest.repositories.SecondaryRepository

class AgendaSharedViewModel(application: Application): AndroidViewModel(application) {

    private val repository = SecondaryRepository(application)

    fun getAgenda(): LiveData<List<Agenda>> {
        return repository.getAgendaList()
    }

    fun getSession(session: Int): Sessions?{
        return repository.getSession(session)
    }

    fun getSpeaker(name: String): Speakers?{
        return repository.getSpeaker(name)
    }

    fun getSpeakerByName(name: String): Speakers?{
        return repository.getSpeakerByName(name)
    }
}