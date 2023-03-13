package eu.funventure.testapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.funventure.testapp.domain.NumberRepository
import eu.funventure.testapp.domain.model.NumberInfo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: NumberRepository) : ViewModel() {

    private val _number = MutableSharedFlow<Int>()
    val number = _number.asSharedFlow()

    private val _numbersInfo = MutableStateFlow<List<NumberInfo>>(listOf())
    val numbersInfo = _numbersInfo.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    init {
        loadAllNumbersInfo()
    }

    fun randomNumberClick() {
        viewModelScope.launch {
            val n = repository.getRandomNumber()
            val description = repository.getDescriptionByNumber(n)
            repository.saveNumberInfo(NumberInfo(value = n, description = description))
            loadAllNumbersInfo()
            _number.emit(n)
        }
    }

    fun numberFromUseClick(value: String) {
        viewModelScope.launch {
            val n = value.toIntOrNull()
            if (n == null) {
                _error.emit("Enter a number")
                return@launch
            }
            val description = repository.getDescriptionByNumber(n)
            repository.saveNumberInfo(NumberInfo(value = n, description = description))
            loadAllNumbersInfo()
            _number.emit(n)
        }
    }

    private fun loadAllNumbersInfo() {
        viewModelScope.launch {
            _numbersInfo.emit(repository.getAllNumberInfo())
        }
    }
}