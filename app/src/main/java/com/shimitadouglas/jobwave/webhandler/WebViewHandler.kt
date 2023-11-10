package com.shimitadouglas.jobwave.webhandler

import android.content.Context
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Message
import android.webkit.ConsoleMessage
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewHandler(context: Context) {
    //declaration of webview object
    private var webView: WebView = WebView(context)

    //webView needs webChromeClient and the webClient

    private var webChromeClient = object : WebChromeClient() {
        //invoked when window is created
        override fun onCreateWindow(
            view: WebView?,
            isDialog: Boolean,
            isUserGesture: Boolean,
            resultMsg: Message?
        ): Boolean {
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg)
        }

        //invoked when console console message show
        override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
            return super.onConsoleMessage(consoleMessage)
        }
    }

    private var webClient = object : WebViewClient() {

        //invoked when the page is loading
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        //invoked when page has finished loading
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
        }

        //invoked when is http error
        override fun onReceivedHttpError(
            view: WebView?,
            request: WebResourceRequest?,
            errorResponse: WebResourceResponse?
        ) {
            super.onReceivedHttpError(view, request, errorResponse)
        }

        //invoked when SSl error
        override fun onReceivedSslError(
            view: WebView?,
            handler: SslErrorHandler?,
            error: SslError?
        ) {
            super.onReceivedSslError(view, handler, error)
        }

        //invoked when is general error
        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
        }
    }


    init {
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

        //set support for sharing files
        webSettings.allowFileAccess = true
        //enable db
        webSettings.databaseEnabled = true

        //allow access to the content
        webSettings.allowContentAccess = true
        //js window opens automatically
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        //wide screen capability
        webSettings.useWideViewPort = true

        //enable dom content
        webSettings.domStorageEnabled = true

        //fun to init all the webContent
        initAllWebContents()
    }

    private fun initAllWebContents() {
        //init the all chrome related contents
        webView.webChromeClient = webChromeClient

        //init all the webClient content
        webView.webViewClient = webClient
    }


    //loads the external url passed by the system
    private fun loadExternalUrl(url: String) {
        webView.loadUrl(url)

    }

    //refreshes or reloads the whole webview/web
    private fun refreshWeb() {
        webView.reload()
    }


}

