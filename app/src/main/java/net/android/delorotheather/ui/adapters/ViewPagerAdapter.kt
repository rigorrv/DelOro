package net.android.delorotheather.ui.adapters

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import net.android.delorotheather.ui.ContainerFragment
import net.android.delorotheather.ui.fragments.*

class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    val items = 3
    override fun getItemCount(): Int {
        return items
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> MovieFragment()
            2 -> FindUsFragment()
            else -> HomeFragment()
        }
    }

    override fun getItemId(position: Int): Long {
        ContainerFragment.getCurrentTab()
        return super.getItemId(position)
    }
}