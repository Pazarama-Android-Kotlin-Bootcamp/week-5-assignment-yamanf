package com.merttoptas.retrofittutorial.ui.favorite

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.merttoptas.retrofittutorial.R
import com.merttoptas.retrofittutorial.data.model.PostDTO
import com.merttoptas.retrofittutorial.databinding.FragmentFavoriteBinding
import com.merttoptas.retrofittutorial.ui.favorite.adapter.FavoriteAdapter
import com.merttoptas.retrofittutorial.ui.favorite.adapter.OnPostClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteFragment : Fragment() , OnPostClickListener {
    private lateinit var binding : FragmentFavoriteBinding
    private lateinit var favAdapter : FavoriteAdapter
    private var mAdapter = FavoriteAdapter(this@FavoriteFragment)
    private val viewModel by viewModels<FavoriteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        favAdapter = FavoriteAdapter(this@FavoriteFragment)
        favAdapter.notifyDataSetChanged()
        */

        println("onCreate")
        Log.i("FavoriteFragment", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        println("onCreateView")
        mAdapter.notifyDataSetChanged()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("FavoriteFragment", "onViewCreated")
        viewModel.favoritePostLiveData.observe(viewLifecycleOwner) {
            it?.let {
                binding.rvFavoriteList.adapter = FavoriteAdapter(this@FavoriteFragment).apply {
                    submitList(it)
                    mAdapter.notifyDataSetChanged()
                }
            } ?: kotlin.run {
                Toast.makeText(requireContext(), "No favorite data", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onPostClick(post: PostDTO) {
        viewModel.onFavoritePost(post)
    }



    override fun onDestroy() {
        super.onDestroy()
        Log.i("FavoriteFragment", "onDestroy")
    }

}