package net.android.delorotheather.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.register_fragment.*
import net.android.delorotheather.R
import net.android.delorotheather.model.users.UserItem
import net.android.delorotheather.ui.ContainerFragment
import net.android.delorotheather.ui.IntroFragment.Companion.loginInfo
import net.android.delorotheather.viewmodel.MyViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class LoginFragment : Fragment(R.layout.login_fragment) {


    var userTxt = ""
    var passOneTxt = ""
    val myViewModel: MyViewModel by viewModel()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar?.hide()


        checkUser()
        myViewModel.loginUser()
        Log.d("TAG", "users: ${loginInfo}")

        wronMsg.setText("")


        loginBtn.setOnClickListener {
            userTxt = userText.text.toString()
            passOneTxt = passText.text.toString()
            myViewModel.loginUser()
            if (checkUser() && checkEmpty()) {
                Log.d("TAG", "onActivityCreated: Login")
                findNavController().navigate(R.id.go_main)
            } else {
                wronMsg.setText("Something is wrong")
            }
            val `in`: InputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            `in`.hideSoftInputFromWindow(
                passText.getWindowToken(),
                0
            )
            userText.setText("")
            passText.setText("")

        }
        registerBtn.setOnClickListener {
            findNavController().navigate(R.id.go_register)
        }
    }

    fun checkEmpty(): Boolean {
        return (!userTxt.isEmpty() && !passOneTxt.isEmpty())
    }


    fun checkUser(): Boolean {
        var case1 = false
        var case2 = false
        Log.d("TAG", "checkUser: ${loginInfo}")
        for (check in loginInfo) {
            if (check !== null) {
                if (check.email == userTxt)
                    case1 = true
                if (check.pass == passOneTxt)
                    case2 = true
            }

        }
        Log.d("TAG", "checkUser: $case1 \n $case2")
        return case1 && case2
    }
}