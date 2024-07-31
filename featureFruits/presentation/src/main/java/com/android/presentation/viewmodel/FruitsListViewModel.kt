package com.android.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.common.network.Resource
import com.android.domain.usecases.FruitsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing the state and interactions for the fruits list.
 */
@HiltViewModel
class FruitsListViewModel @Inject constructor(
    private val fruitsUseCase: FruitsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<Resource<Any>?>(null)
    val state: StateFlow<Resource<Any>?> get() = _state

    /**
     * Fetches fruits and updates the view state accordingly.
     */
    fun getFruitsNew() = viewModelScope.launch {
        _state.value = Resource.Loading()

        fruitsUseCase.getFruits().fold(onSuccess = { _state.value = Resource.Success(it) },
            onFailure = { _state.value = Resource.Error(it.message ?: "An error occurred") })
    }
}
