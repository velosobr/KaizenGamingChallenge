package com.velosobr.kaizengaming.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.velosobr.kaizengaming.domain.model.Sport
import com.velosobr.kaizengaming.domain.usecases.GetSportsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SportsViewModelTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    // Mock dependencies
    private val getSportsUseCase = mockk<GetSportsUseCase>()
    private val stateObserver = mockk<Observer<MainActivityState>>(relaxed = true)

    private lateinit var viewModel: SportsViewModel
    @Before
    fun setUp() {
        viewModel = SportsViewModel(getSportsUseCase)
        viewModel.state.observeForever(stateObserver)
    }
    @Test
    fun `fetchSports returns Success state when use case response is successful`() = runBlocking {
        Dispatchers.setMain(testDispatcher)


            val expectedSport = Sport("1", "Soccer", emptyList())
            coEvery { getSportsUseCase.invoke() } returns mutableListOf(expectedSport)

        val observer = spyk<Observer<MainActivityState>>()
        viewModel.state.observeForever(observer)


            viewModel.fetchSports()


        coVerify { getSportsUseCase.invoke() }
        verify { observer.onChanged(MainActivityState.Success(mutableListOf(expectedSport))) }

        viewModel.state.removeObserver(observer)
        Dispatchers.resetMain()

    }
}