package uz.triples.gdgdevfest.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_f_a_q.*
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.adaptors.FAQRVA
import uz.triples.gdgdevfest.viewModels.FAQViewModel

class FAQFragment : Fragment(R.layout.fragment_f_a_q) {

    private lateinit var viewModel: FAQViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel = ViewModelProviders.of(this).get(FAQViewModel::class.java)
        FAQRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getAllFAQ().observe(viewLifecycleOwner, {
            FAQRecyclerView.adapter = FAQRVA(it)
        })
    }
}