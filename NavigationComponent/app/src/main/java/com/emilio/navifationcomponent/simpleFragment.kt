package com.emilio.navifationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class simpleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_simple, container, false)

        view.findViewById<Button>(R.id.bntAccion).setOnClickListener {

            findNavController().navigate(R.id.action_global_mainFragment)
        }
        return view
    }

}