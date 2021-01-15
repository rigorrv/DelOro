package net.android.delorotheather.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.intro_fragment.*
import net.android.delorotheather.R
import net.android.delorotheather.model.users.UserItem
import net.android.delorotheather.networking.Connection
import net.android.delorotheather.viewmodel.MyViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class IntroFragment : Fragment(R.layout.intro_fragment) {

    val myViewModel: MyViewModel by viewModel()

    companion object {
        lateinit var view: View
        var loginInfo: List<UserItem> = mutableListOf()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        myViewModel.checkDB()
        super.onActivityCreated(savedInstanceState)
        myViewModel.loginUser()
        myViewModel.loadMovieTopSQL()

        (activity as AppCompatActivity?)!!.supportActionBar?.hide()
        if (Connection.isConnected()) {
            myViewModel.getMoviesTopList()
            myViewModel.findnMovie("ana")
            myViewModel.getInfo()
            val uri: Uri =
                Uri.parse("https://firebasestorage.googleapis.com/v0/b/abt-map-firebase.appspot.com/o/home%2Flogo.mp4?alt=media&token=461c87d5-e994-4d07-b49d-38c9d2e74991")
            videoView.setVideoURI(uri)
            videoView.requestFocus()
            videoView.start()
            videoView.setOnCompletionListener {
                findNavController().navigate(R.id.go_login)
            }

        } else {
            myViewModel.checkDB()
            myViewModel.emptySQL.observe(viewLifecycleOwner, Observer {
                if (it)
                    findNavController().navigate(R.id.go_login)
                else
                    findNavController().navigate(R.id.go_error)
            })
        }

    }


}
