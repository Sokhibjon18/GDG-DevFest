package uz.triples.gdgdevfest.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_main.*
import uz.triples.gdgdevfest.R

class HomeFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        agendaCardView.setOnClickListener {
            findNavController().navigate(R.id.agendaFragment)
        }
    }
}