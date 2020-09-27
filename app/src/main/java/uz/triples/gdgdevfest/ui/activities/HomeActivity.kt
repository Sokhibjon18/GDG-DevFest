package uz.triples.gdgdevfest.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.viewModels.MainViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.updateJson()
    }
}