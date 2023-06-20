package my.masdico.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private var _displayName: String = ""
    val displayName: String get() = _displayName
    fun changeName(name: String){
        _displayName = name
    }
}