package eu.funventure.testapp.presentation.number_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.funventure.testapp.domain.NumberRepository
import eu.funventure.testapp.domain.model.NumberInfo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NumberInfoViewModel @Inject constructor(private val repository: NumberRepository) :
    ViewModel() {

    private val _info = MutableSharedFlow<NumberInfo>(replay = 1)
    val info = _info.asSharedFlow()

    fun setNumber(n: Int) {
        viewModelScope.launch {
            _info.emit(repository.getNumberInfoByNumber(n))
        }
    }
}
