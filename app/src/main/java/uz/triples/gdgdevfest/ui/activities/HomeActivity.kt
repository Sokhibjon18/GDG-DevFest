package uz.triples.gdgdevfest.ui.activities

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.repositories.MainRepository

class HomeActivity : AppCompatActivity() {

    private lateinit var repository: MainRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        repository = MainRepository(application)

        repository.updateDatabase()

        if (!isNetworkConnected()) {
            GlobalScope.launch {
                Thread.sleep(3000)
                withContext(Dispatchers.Main) {
                    Snackbar.make(
                        container,
                        "No Connection, Please turn on mobile data",
                        Snackbar.LENGTH_INDEFINITE
                    ).setDuration(5000).setActionTextColor(Color.WHITE).show()
                }
            }
        }

    }

    private fun isNetworkConnected(): Boolean {
        val cm =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
    }

    override fun onBackPressed() {
        val id = findNavController(R.id.fragmentContainer).currentDestination!!.id
        if (id == R.id.homeFragment || id == R.id.splashFragment)
            super.onBackPressed()
        else if (id == R.id.agendaFragment || id == R.id.speakersFragment || id == R.id.teamFragment
            || id == R.id.sponsorFragment || id == R.id.mapsFragment || id == R.id.FAQFragment
        ) {
            findNavController(R.id.fragmentContainer).popBackStack()
        }
    }
}