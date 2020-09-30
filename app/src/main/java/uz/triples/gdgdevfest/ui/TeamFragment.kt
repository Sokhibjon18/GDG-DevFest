package uz.triples.gdgdevfest.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_team.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.adaptors.TeamMembersRVA
import uz.triples.gdgdevfest.adaptors.TeamRVA
import uz.triples.gdgdevfest.database.entities.TeamMembers
import uz.triples.gdgdevfest.interfaces.StringTransitionInterface
import uz.triples.gdgdevfest.viewModels.TeamViewModel

class TeamFragment : Fragment(R.layout.fragment_team) {

    private lateinit var viewModel: TeamViewModel
    private val TAG = "TeamFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(TeamViewModel::class.java)

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.getTeams().observe(viewLifecycleOwner, {
            teamNameRecyclerView.adapter = TeamRVA(it, object : StringTransitionInterface {
                override fun getString(name: String?) {
                    var listTeamMembers = listOf<TeamMembers>()
                    GlobalScope.launch {
                        listTeamMembers = viewModel.getAllMembers(name)
                        var adapter: TeamMembersRVA
                        withContext(Dispatchers.Main){
                            teamMembersRecyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                            teamMembersRecyclerView.adapter = TeamMembersRVA(listTeamMembers)
                        }
                    }
                }
            })
            teamNameRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        })
    }
}