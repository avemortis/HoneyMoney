package com.vtorushin.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.vtorushin.app.R
import com.vtorushin.app.di.component
import com.vtorushin.feature.authoption.getAuthOptionScreen
import com.vtorushin.feature.registration.getRegistrationScreen

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy { component().viewModel() }
    private val router by lazy { component().router() }
    private val navigatorHolder by lazy { component().navigatorHolder() }
    private val navigator = AppNavigator(this, R.id.root)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            router.newRootScreen(viewModel.getStartScreen())
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}