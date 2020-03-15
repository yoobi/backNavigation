package com.application.backNavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_1.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

class Fragment1 : Fragment(R.layout.fragment_1) {
    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        counter = savedInstanceState?.getInt("counter") ?: 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvCount.text = counter.toString()

        btnProceed.setOnClickListener { navController.navigate(R.id.action_fragment_1_to_fragment_2) }
        btnDec.setOnClickListener { tvCount.text = "${--counter}" }
        btnInc.setOnClickListener { tvCount.text = "${++counter}" }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter", counter)
    }
}

class Fragment2 : Fragment(R.layout.fragment_2) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnProceed.setOnClickListener { navController.navigate(R.id.action_fragment_2_to_fragment_3) }
    }

}

class Fragment3 : Fragment(R.layout.fragment_3) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnProceed.setOnClickListener { navController.navigate(R.id.action_fragment_3_to_fragment_1) }
    }

}

private val Fragment.navController get() = Navigation.findNavController(requireActivity(), R.id.navFragment)