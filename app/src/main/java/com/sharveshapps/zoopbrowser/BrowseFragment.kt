package com.sharveshapps.zoopbrowser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sharveshapps.zoopbrowser.databinding.FragmentBrowseBinding

class BrowseFragment : Fragment() {

    private lateinit var binding: FragmentBrowseBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_browse, container, false)
        binding = FragmentBrowseBinding.bind(view)
        binding.webView.loadUrl("https://www.google.com")
        return view
    }


}