package uz.triples.gdgdevfest.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.triples.gdgdevfest.database.entities.Agenda
import uz.triples.gdgdevfest.repositories.MainRepository

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = MainRepository(application)

    fun updateJson(){
            repository.updateAgenda()
    }
}