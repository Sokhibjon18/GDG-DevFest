package uz.triples.gdgdevfest.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.repositories.MainRepository

class HomeActivity : AppCompatActivity() {

    private lateinit var repository: MainRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        repository = MainRepository(application)

        repository.updateDatabase()
    }

    override fun onBackPressed() {
        val id = findNavController(R.id.fragmentContainer).currentDestination!!.id
        if (id == R.id.homeFragment || id == R.id.splashFragment)
            super.onBackPressed()
        else if (id == R.id.agendaFragment || id == R.id.speakersFragment || id == R.id.teamFragment
            || id == R.id.sponsorFragment || id == R.id.mapsFragment || id == R.id.FAQFragment){
            findNavController(R.id.fragmentContainer).popBackStack()
        }
    }
}