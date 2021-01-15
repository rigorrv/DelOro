package net.android.delorotheather

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_container.*
import net.android.delorotheather.networking.background
import net.android.delorotheather.ui.ContainerFragment
import net.android.delorotheather.ui.ContainerFragment.Companion.rootView
import net.android.delorotheather.ui.fragmentLocation

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Glide.with(this)
            .load(background)
            .into(appBackground)
    }

    override fun onBackPressed() {
        if (ContainerFragment.mainFragment) {
            super.onBackPressed()
            ContainerFragment.mainFragment = false
        }
    }
}