package com.velosobr.kaizengaming.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.velosobr.kaizengaming.R
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.lifecycleScope
import androidx.activity.viewModels

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val sportsViewModel: SportsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launchWhenStarted {
            sportsViewModel.sports.collect { sports ->
                // Aqui você pode atualizar sua interface do usuário com os esportes
                // Por exemplo, você pode definir o texto de uma TextView para a lista de esportes
                println(sports)
            }
        }
    }
}