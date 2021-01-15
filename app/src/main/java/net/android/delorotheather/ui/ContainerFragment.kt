package net.android.delorotheather.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_container.*
import kotlinx.android.synthetic.main.fragment_container.view.*
import net.android.delorotheather.R
import net.android.delorotheather.networking.background
import net.android.delorotheather.ui.adapters.ViewPagerAdapter
import net.android.delorotheather.ui.fragments.CommentsFragment
import net.android.delorotheather.ui.fragments.FindUsFragment
import net.android.delorotheather.ui.fragments.HomeFragment
import net.android.delorotheather.ui.fragments.MovieFragment
import net.android.delorotheather.viewmodel.MyViewModel
import org.koin.android.viewmodel.ext.android.viewModel

var fragmentLocation = 0

class ContainerFragment : Fragment() {


    val myViewModel: MyViewModel by viewModel()

    companion object {
        lateinit var rootView: View
        fun getCurrentTab() {
            rootView.tabLayout.selectTab(rootView.tabLayout.getTabAt(rootView.viewPager.currentItem))
        }

        var mainFragment = false

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_container, container, false)
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val colorDrawable = ColorDrawable(Color.parseColor("#E63D2E"))
        (activity as AppCompatActivity?)!!.supportActionBar?.show()
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar?.setBackgroundDrawable(colorDrawable)
        (activity as AppCompatActivity?)!!.supportActionBar?.setLogo(R.drawable.ic_logotop)
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayUseLogoEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayShowTitleEnabled(false)


        Glide.with(this)
            .load(background)
            .into(rootView.bgImg)

        myViewModel.jsonInfoLiveData.observe(viewLifecycleOwner, Observer {
            HomeFragment.jsonInfo = listOf(it)
            MovieFragment.jsonInfo = listOf(it)
            FindUsFragment.jsonInfo = listOf(it)
            CommentsFragment.jsonInfo = listOf(it)
            var viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
            viewPager.adapter = viewPagerAdapter
        })
        myViewModel.moviewTopListLiveData.observe(viewLifecycleOwner, Observer {
            HomeFragment.movieJson = listOf(it)
        })

        myViewModel.loginLiveData.observe(viewLifecycleOwner, Observer {
            Log.d("TAG", "onActivityCreated: ${it}")
        })
        rootView.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                rootView.viewPager.currentItem = tab.position
                Log.d("TAG", "onTabSelected: ${tab.position}")
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.loginBtn -> {
            }
            R.id.emailBtn -> {
                Log.d("TAG", "onOptionsItemSelected: Email")
            }
            R.id.logOut -> {
                mainFragment = false
                findNavController().navigate(R.id.main_to_login)
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }
}