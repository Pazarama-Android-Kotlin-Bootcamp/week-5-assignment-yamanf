package com.merttoptas.retrofittutorial.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merttoptas.retrofittutorial.data.local.database.entity.PostEntity
import com.merttoptas.retrofittutorial.data.model.PostDTO
import com.merttoptas.retrofittutorial.data.repository.Favorite.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteRepository: FavoriteRepository) : ViewModel() {

    private var _favoritePostLiveData = MutableLiveData<List<PostDTO>>()
    val favoritePostLiveData : LiveData<List<PostDTO>>
        get() = _favoritePostLiveData


    private var _postCacheData = MutableLiveData<List<PostDTO>?>()
    private val postCacheData: LiveData<List<PostDTO>?>
        get() = _postCacheData


    init {
        getAllFavoritePost()
    }


    private fun getAllFavoritePost()  {
        _favoritePostLiveData.postValue(favoriteRepository.getAllFavoritePost().map { PostEntity ->
            PostDTO(
                id = PostEntity.id,
                userId = null,
                isFavorite = false,
                body = PostEntity.postBody ,
                title = PostEntity.postTitle
            )
        })
    }

    fun onFavoritePost(post: PostDTO) {
        post.id?.let {
            favoriteRepository.getPostById(it)?.let {
                favoriteRepository.deleteFavoritePost(
                    PostEntity(
                        id = post.id.toLong(),
                        postTitle = post.title,
                        postBody = post.body
                    )
                )
            }
        } ?: kotlin.run {
            favoriteRepository.insertFavoritePost(
                PostEntity(
                    id = post.id,
                    postTitle = post.title,
                    postBody = post.body
                )
            )
        }
    }


}