package com.example.themovie.presenter.main.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.themovie.R
import com.example.themovie.databinding.FragmentMovieDetailsBinding
import com.example.themovie.domain.model.Movie
import com.example.themovie.presenter.main.movie_details.adapter.CastAdapter
import com.example.themovie.presenter.main.movie_details.adapter.ViewPagerAdapter
import com.example.themovie.util.StateView
import com.example.themovie.util.initToolbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val args: MovieDetailsFragmentArgs by navArgs()

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    //Instância de movieDetailsViewModel na activity
    private val movieDetailsViewModel: MovieDetailsViewModel by activityViewModels()

    private lateinit var castAdapter: CastAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(toolbar = binding.toolbar, lightIcon = true)

        getMovieDetails()

        initRecyclerCredits()

        configTabLayout()
    }

    private fun configTabLayout() {
        movieDetailsViewModel.setMovieId(movieId = args.movieId)

        val viewPagerAdapter = ViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = viewPagerAdapter

        viewPagerAdapter.addFragment(
            fragment = TrailersFragment(),
            title = R.string.text_title_trailers_tab_layout
        )

        viewPagerAdapter.addFragment(
            fragment = SimilarFragment(),
            title = R.string.text_title_similar_tab_layout
        )

        viewPagerAdapter.addFragment(
            fragment = CommentsFragment(),
            title = R.string.text_title_comments_tab_layout
        )

        binding.viewPager.offscreenPageLimit = viewPagerAdapter.itemCount
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab, position ->
            tab.text = getString(viewPagerAdapter.getTitle(position))
        }.attach()

    }

    private fun getMovieDetails() {
        movieDetailsViewModel.getMovieDetails(movieId = args.movieId)
            .observe(viewLifecycleOwner) { stateView ->
                when (stateView) {
                    is StateView.Loading -> {

                    }
                    is StateView.Success -> {
                        configData(movie = stateView.data)
                    }
                    is StateView.Error -> {

                    }
                }
            }
    }

    private fun initRecyclerCredits() {
        castAdapter = CastAdapter()

        with(binding.recyclerCast) {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = castAdapter
        }

    }

    private fun getCredits() {
        movieDetailsViewModel.getCredits(movieId = args.movieId)
            .observe(viewLifecycleOwner) { stateView ->
                when (stateView) {
                    is StateView.Loading -> {

                    }
                    is StateView.Success -> {
                        castAdapter.submitList(stateView.data?.cast)
                    }
                    is StateView.Error -> {

                    }
                }
            }
    }

    private fun configData(movie: Movie?) {
        Glide
            .with(requireContext())
            .load("https://image.tmdb.org/t/p/w500${movie?.posterPath}")
            .into(binding.imgMovie)

        binding.textMovie.text = movie?.title

        binding.textVoteAverage.text = String.format("%.1f", movie?.voteAverage)

        //Forma para selecionar somente o ano da api que possui retorno "yyyy-MM-dd"
        val originalFormat = SimpleDateFormat("yyyy-mm-dd", Locale.ROOT)
        val data = originalFormat.parse(movie?.releaseDate ?: "")

        val yearFormat = SimpleDateFormat("yyyy", Locale.ROOT)
        val year = yearFormat.format(data)
        binding.textReleaseDate.text = year

        binding.textProductionCountry.text = movie?.productionCountries?.get(0)?.name ?: ""

        val genres = movie?.genres?.map { it.name }?.joinToString(", ")
        binding.textGenres.text =
            getString(R.string.text_all_genres_movies_details_fragment, genres)

        binding.textDescription.text = movie?.overview

        getCredits()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}