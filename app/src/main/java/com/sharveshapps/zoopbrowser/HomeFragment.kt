package com.sharveshapps.zoopbrowser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.sharveshapps.zoopbrowser.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.bind(view)
        return view
    }

    override fun onResume() {
        super.onResume()
        val mainActivityRef= requireActivity() as MainActivity
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(result: String?): Boolean {
               if (mainActivityRef.checkForInternet(requireContext()))
                   mainActivityRef.changeTab(result!!,BrowseFragment(result))
                else
                    Snackbar.make(binding.root,"Internet Not Connected \ud83d\ude11",3000).show()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean =false

        })
    }


}