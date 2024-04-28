// MainActivity.kt
package com.velosobr.kaizengaming.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.velosobr.kaizengaming.databinding.ActivityMainBinding
import com.velosobr.kaizengaming.presentation.adapter.SportAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val sportsViewModel: SportsViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val sportsAdapter = SportAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                sportsViewModel.sports.collect { result ->
                    if (result.isSuccess) {
                        val sports = result.getOrNull()
                        sportsAdapter.updateSports(sports ?: listOf())

                    } else {
                        val exception = result.exceptionOrNull()
                        // TODO Exibir uma mensagem de erro apropriada
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvSports.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = sportsAdapter
        }
    }
}