package uz.triples.gdgdevfest.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import uz.triples.gdgdevfest.database.entities.Team
import uz.triples.gdgdevfest.database.entities.TeamMembers
import uz.triples.gdgdevfest.repositories.SecondaryRepository

class TeamViewModel(application: Application): AndroidViewModel(application) {

    private val repository = SecondaryRepository(application)

    fun getAllMembers(teamName: String?): List<TeamMembers> {
        return repository.getTeamMembers(teamName)
    }

    fun getTeams(): LiveData<List<Team>> {
        return repository.getTeams()
    }
}