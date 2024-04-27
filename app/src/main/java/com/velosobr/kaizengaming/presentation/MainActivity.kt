package com.velosobr.kaizengaming.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.velosobr.kaizengaming.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val sportsViewModel: SportsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                sportsViewModel.sports.collect { result ->
                    if (result.isSuccess) {
                        val sports = result.getOrNull()
                        Toast.makeText(this@MainActivity, "Fetched sports: $sports", Toast.LENGTH_LONG).show()
                        // TODO Atualizar a interface do usuário com os esportes
                    } else {
                        val exception = result.exceptionOrNull()
                        Toast.makeText(this@MainActivity, "Error fetching sports: $exception", Toast.LENGTH_LONG).show()
                        // TODO Exibir uma mensagem de erro apropriada
                    }
                }
            }
        }
    }
}