package my.masdico.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private var _displayTitle: String = "NBA Match"
    private var _scoreTeamA: Int = 0
    private var _scoreTeamB: Int = 0
    val displayTitle: String get() = _displayTitle
    val scoreTeamA: String get() = _scoreTeamA.toString()
    val scoreTeamB: String get() = _scoreTeamB.toString()

    fun addScoreTeam(point: Int, team: String){
        if (team == "A"){
            _scoreTeamA += point
        }else{
            _scoreTeamB += point
        }
    }

    fun resetScore(){
        _scoreTeamA = 0
        _scoreTeamB = 0
    }
}