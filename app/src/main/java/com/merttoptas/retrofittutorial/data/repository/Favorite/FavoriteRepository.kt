package com.merttoptas.retrofittutorial.data.repository.Favorite

import com.merttoptas.retrofittutorial.data.local.database.entity.PostEntity

interface FavoriteRepository {
    fun insertFavoritePost(post: PostEntity)
    fun deleteFavoritePost(post: PostEntity)
    fun getPostById(id: Long): PostEntity?
    fun getAllFavoritePost() : List<PostEntity>
}