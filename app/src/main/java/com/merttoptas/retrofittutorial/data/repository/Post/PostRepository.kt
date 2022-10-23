package com.merttoptas.retrofittutorial.data.repository.Post

import com.merttoptas.retrofittutorial.data.local.database.entity.PostEntity
import com.merttoptas.retrofittutorial.data.model.Post
import retrofit2.Call

/**
 * Created by merttoptas on 16.10.2022.
 */

interface PostRepository {
    fun getPosts(): Call<List<Post>>
    fun getPostById(id: Long): PostEntity?
    fun insertFavoritePost(post: PostEntity)
    fun deleteFavoritePost(post: PostEntity)

}