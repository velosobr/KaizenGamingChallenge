package com.velosobr.kaizengaming.presentation

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.velosobr.kaizengaming.R
import com.velosobr.kaizengaming.databinding.ActivityMainBinding
import com.velosobr.kaizengaming.presentation.adapter.SportAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val sportsViewModel: SportsViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var sportsAdapter: SportAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setFullScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        sportsViewModel.state.observe(this) { state ->
            when (state) {
                is MainActivityState.Loading -> showLoadingState()
                is MainActivityState.Success -> showSuccessState(state)
                is MainActivityState.Error -> showErrorState(state.message)
            }
        }
        sportsViewModel.fetchSports()

    }

    private fun setFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            @Suppress("DEPRECATION")
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    private fun showSuccessState(state: MainActivityState.Success) {

        with(binding) {
            includeViewShimmerSportState.shimmerSportItems.visibility = View.GONE
            binding.rvSports.visibility = View.VISIBLE
            binding.tvMessage.visibility = View.GONE
            buttonRetry.visibility = View.GONE

            sportsAdapter = SportAdapter(state.sports, this@MainActivity)

            with(rvSports){
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = sportsAdapter
            }
        }
    }

    private fun showErrorState(message: String) {
        with(binding){
            includeViewShimmerSportState.shimmerSportItems.visibility = View.GONE
            binding.rvSports.visibility = View.GONE
            Log.d("MainActivity", "showErrorState: $message")
            binding.tvMessage.text = message
            binding.tvMessage.visibility = View.VISIBLE
            buttonRetry.visibility = View.VISIBLE
            buttonRetry.setOnClickListener {
                sportsViewModel.fetchSports()
            }
        }
    }

    private fun showLoadingState() {
        with(binding){
            includeViewShimmerSportState.shimmerSportItems.visibility = View.VISIBLE
            rvSports.visibility = View.GONE
            tvMessage.visibility = View.GONE
            buttonRetry.visibility = View.GONE
        }
    }
}
