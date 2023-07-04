package my.masdico.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private var _displayTitle: String = "NBA Match"
    val displayTitle: String get() = _displayTitle
    private var _score = mutableMapOf <String,Int>()
    val score: MutableMap<String,Int> get() = _score

    fun changeTitle(title: String){
        _displayTitle = title
    }

    fun addScoreTeam(point: Int, teamId: String){
        if (_score[teamId] != null) _score.put(teamId, _score[teamId]!!.plus(point)) else _score.put(teamId, point)
        /* merge requires minimum API level 24
        if (_score[teamId] != null) _score.merge(teamId, point, Int::plus) else _score.put(teamId, point)
        */
    }

    fun resetScore(homeId: String, allyId: String){
        _score.remove(homeId)
        _score.remove(allyId)
    }
}