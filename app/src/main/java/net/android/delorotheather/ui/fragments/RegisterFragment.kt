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
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.register_fragment.*
import net.android.delorotheather.R
import net.android.delorotheather.viewmodel.MyViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment(R.layout.register_fragment) {


    val myViewModel: MyViewModel by viewModel()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loginBtnRegister.setOnClickListener {
            var userTxt = userTextRegister.text.toString()
            var passOneTxt = passTextRegister.text.toString()
            var passTwoTxt = passTextRegister2.text.toString()
            var result = checkUser(
                userTxt, passOneTxt, passTwoTxt
            )
            if (result) {
                myViewModel.register(userTxt, passOneTxt)
                myViewModel.inserUserSQL(userTxt, passOneTxt)
                findNavController().navigate(R.id.go_back_login)
            }

            val `in`: InputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            `in`.hideSoftInputFromWindow(
                passTextRegister2.getWindowToken(),
                0
            )
            myViewModel.loginUser()


        }

        registerBtnRegister.setOnClickListener {
            findNavController().navigate(R.id.go_back_login)
        }
    }

    fun checkUser(user: String, pass: String, passTwo: String): Boolean {
        var case1 = false
        var case2 = false
        case1 = (!user.isEmpty() && !pass.isEmpty() && !passTwo.isEmpty())
        case2 = pass == passTwo
        return case1 && case2
    }


}