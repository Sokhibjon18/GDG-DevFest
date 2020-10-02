package uz.triples.gdgdevfest.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_f_a_q.*
import kotlinx.android.synthetic.main.fragment_f_a_q.backButton
import kotlinx.android.synthetic.main.fragment_f_a_q.shareBtn
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

        changeTheme.setOnClickListener {
            Snackbar.make(
                requireView(),
                "We will add this function soon",
                Snackbar.LENGTH_INDEFINITE
            ).setDuration(2000).setActionTextColor(Color.WHITE).show()
        }

        shareBtn.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=uz.triples.gdgdevfest")
            startActivity(Intent.createChooser(sharingIntent, "Share"))
        }

        viewModel = ViewModelProviders.of(this).get(FAQViewModel::class.java)
        FAQRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getAllFAQ().observe(viewLifecycleOwner, {
            FAQRecyclerView.adapter = FAQRVA(requireContext(), it)
        })
    }
}