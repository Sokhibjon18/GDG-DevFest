package uz.triples.gdgdevfest.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import uz.triples.gdgdevfest.database.entities.Sponsors
import uz.triples.gdgdevfest.repositories.SecondaryRepository

class SponsorsViewModel(application: Application): AndroidViewModel(application) {

    private val repository = SecondaryRepository(application)

    fun getAllSponsors(): LiveData<List<Sponsors>> {
        return repository.getSponsors()
    }
}