package com.shimitadouglas.jobwave.webhandler

import android.content.Context
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Message
import android.util.Log
import android.view.ViewGroup
import android.webkit.ConsoleMessage
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout

class WebViewHandler(
    context: Context,
) {

    companion object {
        private const val TAG = "WebViewHandler"

    }


    //declaration of webview object
    var webView: WebView = WebView(context)

    //webView needs webChromeClient and the webClient

    private var webChromeClient = object : WebChromeClient() {
        //invoked when window is created
        override fun onCreateWindow(
            view: WebView?,
            isDialog: Boolean,
            isUserGesture: Boolean,
            resultMsg: Message?
        ): Boolean {
            Log.d(TAG, "onCreateWindow: ")
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg)
        }

        //invoked when console console message show
        override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {

            Log.d(TAG, "onConsoleMessage: ")

            return super.onConsoleMessage(consoleMessage)
        }
    }

    private var webClient = object : WebViewClient() {

        //invoked when the page is loading
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            Log.d(TAG, "onPageStarted: ")
            //invoke loading

        }

        //invoked when page has finished loading
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            Log.d(TAG, "onPageFinished: ")
            //invoke stop loading
        }

        //invoked when is http error
        override fun onReceivedHttpError(
            view: WebView?,
            request: WebResourceRequest?,
            errorResponse: WebResourceResponse?
        ) {
            super.onReceivedHttpError(view, request, errorResponse)
            Log.d(TAG, "onReceivedHttpError: ")
            //invoke error
        }

        //invoked when SSl error
        override fun onReceivedSslError(
            view: WebView?,
            handler: SslErrorHandler?,
            error: SslError?
        ) {
            super.onReceivedSslError(view, handler, error)

            Log.d(TAG, "onReceivedSslError: ")
            //invoke error
        }

        //invoked when is general error
        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            //change the state of showAlert dialog to true
            Log.d(TAG, "onReceivedError: ")


        }
    }


    init {

        //init the layout
        webView.layoutParams = ViewGroup.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )

        //fun to init all the webContent
        initAllWebContents()
    }

    private fun initAllWebContents() {

        //web settings
        val webSettings = webView.settings
        //enable javascript
        webSettings.javaScriptEnabled
        //enable text automatic sizing text
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        //enable zoom controls
        webSettings.displayZoomControls = false
        //support zoom
        webSettings.supportZoom()
        webSettings.setSupportZoom(true)
        //allow access to the content
        webSettings.allowContentAccess = true
        //wide screen capability
        webSettings.useWideViewPort = true

        //open windows automatically
        webSettings.javaScriptCanOpenWindowsAutomatically

        //support multiple windows
        webSettings.setSupportMultipleWindows(true)
        webSettings.supportMultipleWindows()

        //call fun setChrome and Client
        setUpWebChromeClient()
        setUpWebClient()
    }


    private fun setUpWebChromeClient() {
        //init the all chrome related contents
        webView.webChromeClient = webChromeClient
    }

    private fun setUpWebClient() {
        //init all the webClient content
        webView.webViewClient = webClient
    }

    //loads the external url passed by the system
    fun loadExternalUrl(url: String) {
        webView.loadUrl(url)

    }

    //refreshes or reloads the whole webview/web
    fun refreshWeb() {
        webView.reload()
    }


}

