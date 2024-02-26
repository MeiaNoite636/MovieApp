package com.example.themovie.presentation.main.moviedetails.similar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themovie.MainGraphDirections
import com.example.themovie.R
import com.example.themovie.databinding.FragmentSimilarBinding
import com.example.themovie.presentation.main.bottombar.home.adapter.MovieAdapter
import com.example.themovie.presentation.main.moviedetails.details.MovieDetailsViewModel
import com.example.themovie.util.StateView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SimilarFragment : Fragment() {
    private var _binding: FragmentSimilarBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieAdapter: MovieAdapter

    /* Recuperando a instância do fragment MovieDetailsFragment, devendo passar "activityViewModels()"
    em ambos os fragmentos */
    private val movieDetailsViewModel: MovieDetailsViewModel by activityViewModels()
    private val similarViewModel: SimilarViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSimilarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        initObserver()
    }

    private fun initObserver() {
        movieDetailsViewModel.movieId.observe(viewLifecycleOwner) {
            if (it > 0) getSimilar(it)
        }
    }

    private fun initRecyclerView() {
        movieAdapter = MovieAdapter(
            context = requireContext(),
            layoutInflater = R.layout.movie_genre_item,
            movieClickListener = { movieId ->
                movieId?.let {
                    val action = MainGraphDirections.actionGlobalMovieDetailsFragment(movieId)

                    findNavController().navigate(action)
                }
            }
        )

        val lm = GridLayoutManager(
            requireContext(),
            2
        )

        with(binding.recyclerMovies) {
            adapter = movieAdapter
            setHasFixedSize(true)
            layoutManager = lm
        }
    }

    private fun getSimilar(movieId: Int) {
        similarViewModel.getSimilar(movieId).observe(viewLifecycleOwner) {
            when (it) {
                is StateView.Loading -> {

                }
                is StateView.Success -> {
                    movieAdapter.submitList(it.data)
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