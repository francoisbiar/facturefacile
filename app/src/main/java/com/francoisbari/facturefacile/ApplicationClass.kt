package com.francoisbari.facturefacile

import android.app.Application

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        CompositionRoot.init(this)
    }
}