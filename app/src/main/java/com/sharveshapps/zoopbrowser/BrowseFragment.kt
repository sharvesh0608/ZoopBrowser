package com.sharveshapps.zoopbrowser

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.sharveshapps.zoopbrowser.databinding.FragmentBrowseBinding

class BrowseFragment(private var urlNew: String) : Fragment() {

    lateinit var binding: FragmentBrowseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_browse, container, false)
        binding = FragmentBrowseBinding.bind(view)



        return view
    }
    @SuppressLint("SetJavaScriptEnabled")
    override fun onResume() {
        super.onResume()

        val mainActivityRef = requireActivity() as MainActivity
        binding.webView.apply {
            settings.javaScriptEnabled=true
            settings.setSupportZoom(true)
            settings.builtInZoomControls=true
            settings.displayZoomControls=false
            webViewClient=object :WebViewClient(){
                override fun doUpdateVisitedHistory(
                    view: WebView?,
                    url: String?,
                    isReload: Boolean
                ) {
                    super.doUpdateVisitedHistory(view, url, isReload)
                    mainActivityRef.binding.topSearchBar.text=SpannableStringBuilder(url)
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    mainActivityRef.binding.progressBar.progress=0
                    mainActivityRef.binding.progressBar.visibility= View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    mainActivityRef.binding.progressBar.visibility=View.GONE
                }
            }
            webChromeClient= object:WebChromeClient(){
                override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                    super.onShowCustomView(view, callback)
                    binding.webView.visibility=View.GONE
                    binding.customView.visibility=View.VISIBLE
                    binding.customView.addView(view)
                }

                override fun onHideCustomView() {
                    super.onHideCustomView()
                    binding.webView.visibility=View.VISIBLE
                    binding.customView.visibility=View.GONE
                }

                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    mainActivityRef.binding.progressBar.progress=newProgress
                }
            }
            when{
                URLUtil.isValidUrl(urlNew) -> loadUrl(urlNew)
                urlNew.contains(".com",ignoreCase = true) -> loadUrl(urlNew)
                else ->loadUrl("https://www.google.com/search?q=$urlNew")
            }



        }
    }


}