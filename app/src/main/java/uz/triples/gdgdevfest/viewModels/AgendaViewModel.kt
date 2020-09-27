package uz.triples.gdgdevfest.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.triples.gdgdevfest.database.entities.Agenda
import uz.triples.gdgdevfest.repositories.AgendaRepository

class AgendaViewModel(application: Application): AndroidViewModel(application) {

    private val repository = AgendaRepository(application)

    fun updatePartners(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateAgenda()
        }
        
    }

    fun getAgendaLiveData(): LiveData<List<Agenda>> {
        return repository.getAgenda()
    }
}