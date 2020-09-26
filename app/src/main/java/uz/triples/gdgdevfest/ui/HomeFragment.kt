package uz.triples.gdgdevfest.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.viewModels.HomeViewModel

class HomeFragment : Fragment(R.layout.fragment_main) {
    private lateinit var viewModel: HomeViewModel
    private val database = FirebaseDatabase.getInstance().reference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                database
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

}