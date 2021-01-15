package net.android.delorotheather.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import net.android.delorotheather.model.address.AddressJson
import net.android.delorotheather.model.address.AddressJsonItem
import net.android.delorotheather.model.info.JsonInfo
import net.android.delorotheather.model.movies.CommentsMovie
import net.android.delorotheather.model.movies.FindMovie
import net.android.delorotheather.model.movies.MoviewTopList
import net.android.delorotheather.model.users.UserItem

@Dao
interface PostDao {

    @Query("SELECT * FROM jsoninfo")
    fun getInfo(): JsonInfo

    @Query("SELECT * FROM moviewtoplist")
    fun getmovieTopList(): MoviewTopList

    @Query("SELECT * FROM commentsmovie")
    fun getcommentMovie(): CommentsMovie

    @Query("SELECT * FROM findmovie")
    fun getfindMovie(): FindMovie


    @Query("SELECT * FROM user_table")
    fun getUsers(): UserItem

    @Query("SELECT * FROM addressjsonitem")
    fun getAddress(): AddressJsonItem

    @Insert(onConflict = REPLACE)
    fun insertInfo(vararg jsonInfo: JsonInfo)

    @Insert(onConflict = REPLACE)
    fun insertMovieTop(vararg moviewTopList: MoviewTopList)

    @Insert(onConflict = REPLACE)
    fun insertCommentMovie(vararg commentsMovie: CommentsMovie)

    @Insert(onConflict = REPLACE)
    fun insertFindMovie(vararg findMovie: FindMovie)

    @Insert(onConflict = REPLACE)
    fun insertUserItem(vararg userItem: UserItem)

    @Insert(onConflict = REPLACE)
    fun insertAddress(vararg addressJson: AddressJsonItem)

}