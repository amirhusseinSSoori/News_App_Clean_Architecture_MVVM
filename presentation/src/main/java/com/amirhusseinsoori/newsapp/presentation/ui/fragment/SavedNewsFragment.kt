package com.amirhusseinsoori.newsapp.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.amirhusseinsoori.domain.entity.Article
import com.amirhusseinsoori.newsapp.common.BaseFragment
import com.amirhusseinsoori.newsapp.databinding.FragmentSavedNewsBinding
import com.amirhusseinsoori.newsapp.presentation.adapters.SavedNewsAdapter
import com.amirhusseinsoori.newsapp.presentation.ui.viewModel.SaveViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SavedNewsFragment:BaseFragment<FragmentSavedNewsBinding>(FragmentSavedNewsBinding::inflate),
    SavedNewsAdapter.Interaction {
    private val viewModel: SaveViewModel by viewModels()
    private lateinit var adapter: SavedNewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SavedNewsAdapter(this)
        binding?.rvSavedNews?.adapter = adapter



        onCollectSavedNews()

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN ,
            ItemTouchHelper.LEFT or ItemTouchHelper.DOWN){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val article = adapter.differ.currentList[position]
                viewModel.deleteNewsArticle(article)
                Snackbar.make(view ,"Successfully deleted article" , Snackbar.LENGTH_LONG).apply {
                    setAction("Undo"){
                        viewModel.insertArticle(article)
                    }
                    show()
                }

            }

        }

        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding?.rvSavedNews)

    }


    private fun onCollectSavedNews(){

        lifecycleScope.launchWhenStarted {
            viewModel.getAllNews().collect {
                adapter.submitList(it)
                Log.e("TAG", "subscribeOnSavedNews: ${it}", )
            }
        }




    }

    override fun onItemSelected(item: Article) {
        val action = SavedNewsFragmentDirections.actionSavedNewsFragmentToArticleFragment(item)
        findNavController().navigate(action)
    }
}