package uz.triples.gdgdevfest.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import uz.triples.gdgdevfest.database.entities.Speakers
import uz.triples.gdgdevfest.repositories.SecondaryRepository

class SpeakerViewModel(application: Application): AndroidViewModel(application) {

    private val repository = SecondaryRepository(application)

    fun getSpeakers(): LiveData<List<Speakers>> {
        return repository.getSpeakers()
    }
}