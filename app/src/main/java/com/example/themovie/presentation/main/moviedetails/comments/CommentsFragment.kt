package com.example.themovie.presentation.main.moviedetails.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.themovie.databinding.FragmentCommentsBinding
import com.example.themovie.presentation.main.moviedetails.details.MovieDetailsViewModel
import com.example.themovie.util.StateView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsFragment : Fragment() {

    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!

    private lateinit var commentsAdapter: CommentsAdapter

    private val commentsViewModel: CommentsViewModel by viewModels()
    private val movieDetailsViewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        initObserver()
    }

    //Atraves do viewModel compartilhado é possivel ter o id do filme
    private fun initObserver(){
        movieDetailsViewModel.movieId.observe(viewLifecycleOwner){
            if (it>0) getMovieReviews(it)
        }
    }

    private fun initRecycler() {
        commentsAdapter = CommentsAdapter()

        with(binding.recyclerComments) {
            //layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            adapter = commentsAdapter
        }
    }

    private fun getMovieReviews(movieId: Int){
        commentsViewModel.getMovieReviews(movieId).observe(viewLifecycleOwner){stateView ->
            when(stateView){
                is StateView.Loading -> {

                }
                is StateView.Success -> {
                    commentsAdapter.submitList(stateView.data)
                }
                is StateView.Error -> {

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}