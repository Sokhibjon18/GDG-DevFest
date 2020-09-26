package uz.triples.gdgdevfest

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.triples.gdgdevfest.viewModels.AgendaViewModel

class AgendaFragment : Fragment() {

    companion object {
        fun newInstance() = AgendaFragment()
    }

    private lateinit var viewModel: AgendaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_agenda, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AgendaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}