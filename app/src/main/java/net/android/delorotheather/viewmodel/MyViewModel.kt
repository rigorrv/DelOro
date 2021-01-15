package net.android.delorotheather.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import net.android.delorotheather.database.PostDao
import net.android.delorotheather.model.address.AddressJson
import net.android.delorotheather.model.comments.CommentsForm
import net.android.delorotheather.model.info.JsonInfo
import net.android.delorotheather.model.movies.CommentsMovie
import net.android.delorotheather.model.movies.FindMovie
import net.android.delorotheather.model.movies.MovieInfo
import net.android.delorotheather.model.movies.MoviewTopList
import net.android.delorotheather.model.users.User
import net.android.delorotheather.model.users.UserItem
import net.android.delorotheather.networking.*
import net.android.delorotheather.ui.IntroFragment
import net.android.delorotheather.ui.fragments.MoviInfoFragment.Companion.movieID
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel(private val api: Api, private val postDao: PostDao) : ViewModel() {

    //API Info
    var jsonInfoLiveData = MutableLiveData<JsonInfo>()
    var commentsLiveData = MutableLiveData<CommentsForm>()
    var loginLiveData = MutableLiveData<String>()
    var moviewTopListLiveData = MutableLiveData<MoviewTopList>()
    var movieInfoLiveData = MutableLiveData<MovieInfo>()
    var commentsMovieLiveData = MutableLiveData<CommentsMovie>()
    var findMovieLiveData = MutableLiveData<FindMovie>()
    var registerLiveData = MutableLiveData<UserItem>()
    var addressLiveData = MutableLiveData<AddressJson>()
    var emptySQL = MutableLiveData<Boolean>()

    val gson = GsonBuilder().setPrettyPrinting().create()

    init {
        if (Connection.isConnected()) {
            getInfo()
            getMoviesTopList()
            findnMovie("ana")
            getAddress("")
        } else {
            loadInfoSQL()
            loadMovieTopSQL()
            loadCommentMovieSQL()
            loadFindMovieSQL()
        }
    }


    fun checkDB() {
        viewModelScope.launch(Dispatchers.IO + NonCancellable) {
            if (postDao.getmovieTopList() == null) {
                Log.d("TAG", "checkDB: is Empty")
                emptySQL.postValue(false)
            } else {
                Log.d("TAG", "checkDB: is Filled")
                emptySQL.postValue(true)
            }
        }
    }

    fun getInfo() {
        api.getInfo("https://abt-map-firebase.firebaseio.com/data.json")
            .enqueue(object : Callback<JsonInfo> {
                override fun onResponse(call: Call<JsonInfo>, response: Response<JsonInfo>) {
                    jsonInfoLiveData.value = response.body()

                    insertInfoSQL(response.body()!!)
                }

                override fun onFailure(call: Call<JsonInfo>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t}")
                }
            })
    }

    fun login(user: String, pass: String) {
        api.login(urlLogin, user, pass).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                loginLiveData.value = response.body()?.string()

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t}")
            }
        })
    }


    fun register(user: String, pass: String) {
        api.register(registerUrl, user, pass).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                Log.d("TAG", "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("TAG", "register: ${t}")
            }
        })
    }


    fun getComment() {
        api.getComments(commentUrl).enqueue(object : Callback<CommentsForm> {
            override fun onResponse(call: Call<CommentsForm>, response: Response<CommentsForm>) {
                commentsLiveData.value = response.body()
            }

            override fun onFailure(call: Call<CommentsForm>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t}")
            }
        })
    }

    fun getMoviesTopList() {
        api.getMovieTopList(apiKey)
            .enqueue(object : Callback<MoviewTopList> {
                override fun onResponse(
                    call: Call<MoviewTopList>,
                    response: Response<MoviewTopList>
                ) {
                    moviewTopListLiveData.value = response.body()
                    Log.d("TAG", "moviewLogin: ${response.body()}")
                    insertMovieSQL(response.body()!!)
                }

                override fun onFailure(call: Call<MoviewTopList>, t: Throwable) {
                    Log.d("TAG", "movieError: ${t}")
                }
            })
    }

    fun getMovieInfo(movie: Int) {
        api.getMoviInfo(movie, apiKey).enqueue(object : Callback<MovieInfo> {
            override fun onResponse(call: Call<MovieInfo>, response: Response<MovieInfo>) {
                movieInfoLiveData.value = response.body()
            }

            override fun onFailure(call: Call<MovieInfo>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t}")
            }
        })
    }

    fun getCommentsMovie(movie: Int) {
        movieID = 550
        api.getCommentsMovie(movie, apiKey).enqueue(object :
            Callback<CommentsMovie> {
            override fun onResponse(call: Call<CommentsMovie>, response: Response<CommentsMovie>) {
                commentsMovieLiveData.value = response.body()
                Log.d("TAG", "comentarios: ${response.body()}")
                insertCommentMoviSQL(response.body()!!)
            }

            override fun onFailure(call: Call<CommentsMovie>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t}")
            }
        })
    }

    fun findnMovie(movie: String) {
        api.findMovie(movie, apiKey).enqueue(object : Callback<FindMovie> {
            override fun onResponse(call: Call<FindMovie>, response: Response<FindMovie>) {
                findMovieLiveData.value = response.body()
                insertFindMOvieSQL(response.body()!!)
            }

            override fun onFailure(call: Call<FindMovie>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t}")
            }
        })
    }

    fun getAddress(address: String) {
        api.getAddress(addressUrl, address).enqueue(object : Callback<AddressJson> {
            override fun onResponse(call: Call<AddressJson>, response: Response<AddressJson>) {
                Log.d("TAG", "mapa: ${response.body()}")
                addressLiveData.value = response.body()
                insertAddress(response.body()!!)
            }

            override fun onFailure(call: Call<AddressJson>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t}")
            }
        })
    }

    fun insertInfoSQL(jsonInfo: JsonInfo) {
        viewModelScope.async(Dispatchers.IO + NonCancellable) {
            postDao.insertInfo(jsonInfo)
        }
    }

    fun insertMovieSQL(moviewTopList: MoviewTopList) {
        viewModelScope.async(Dispatchers.IO + NonCancellable) {
            postDao.insertMovieTop(moviewTopList)
        }
    }

    fun insertCommentMoviSQL(commentsMovie: CommentsMovie) {
        viewModelScope.async(Dispatchers.IO + NonCancellable) {
            postDao.insertCommentMovie(commentsMovie)
        }
    }

    fun insertFindMOvieSQL(findMovie: FindMovie) {
        viewModelScope.async(Dispatchers.IO + NonCancellable) {
            postDao.insertFindMovie(findMovie)
        }
    }

    fun inserUserSQL(user: String, pass: String) {
        val saveUser = UserItem(user, "1", "", "", pass)
        viewModelScope.async(Dispatchers.IO + NonCancellable) {
            postDao.insertUserItem(saveUser)

        }
    }

    fun insertAddress(addressJson: AddressJson) {
        viewModelScope.async(Dispatchers.IO + NonCancellable) {
            for (address in addressJson) {
                postDao.insertAddress(address)
            }
        }
    }


    fun loginUser() {
        viewModelScope.async(Dispatchers.IO + NonCancellable) {
            registerLiveData.postValue(postDao.getUsers())
            IntroFragment.loginInfo = listOf(postDao.getUsers())
        }
    }


    fun loadInfoSQL() {
        viewModelScope.async(Dispatchers.IO + NonCancellable) {
            jsonInfoLiveData.postValue(postDao.getInfo())
        }
    }

    fun loadMovieTopSQL() {
        viewModelScope.async(Dispatchers.IO + NonCancellable) {
            moviewTopListLiveData.postValue(postDao.getmovieTopList())
            Log.d("TAG", "case 1: ${postDao.getmovieTopList()}")
        }
    }

    fun loadCommentMovieSQL() {
        viewModelScope.async(Dispatchers.IO + NonCancellable) {
            commentsMovieLiveData.postValue(postDao.getcommentMovie())
        }
    }

    fun loadFindMovieSQL() {
        viewModelScope.async(Dispatchers.IO + NonCancellable) {
            findMovieLiveData.postValue(postDao.getfindMovie())
        }
    }


}