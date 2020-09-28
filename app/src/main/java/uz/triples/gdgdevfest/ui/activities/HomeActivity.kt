package uz.triples.gdgdevfest.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
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
//        if (fragmentContainer)
        super.onBackPressed()
    }
}