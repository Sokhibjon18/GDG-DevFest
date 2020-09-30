package uz.triples.gdgdevfest.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import uz.triples.gdgdevfest.database.entities.FAQ
import uz.triples.gdgdevfest.database.entities.Sponsors
import uz.triples.gdgdevfest.repositories.SecondaryRepository

class FAQViewModel(application: Application): AndroidViewModel(application) {

    private val repository = SecondaryRepository(application)

    fun getAllFAQ(): LiveData<List<FAQ>> {
        return repository.getFAQ()
    }
}