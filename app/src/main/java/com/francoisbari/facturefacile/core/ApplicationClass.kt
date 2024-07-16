package com.francoisbari.facturefacile.core

import android.app.Application

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        CompositionRoot.init(this)
    }
}