package uz.triples.gdgdevfest.ui.activities

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_splash.*
import uz.triples.gdgdevfest.R

class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        object : CountDownTimer(2200, 1500) {
            override fun onTick(millisUntilFinished: Long) {
                if (millisUntilFinished < 1500) {
                    imageView2.animate().translationX(-1000f).duration = 700
                    textView2.animate().translationX(1000f).duration = 700
                }
            }

            override fun onFinish() {
                findNavController().navigate(R.id.homeFragment)
            }
        }.start()
    }

}