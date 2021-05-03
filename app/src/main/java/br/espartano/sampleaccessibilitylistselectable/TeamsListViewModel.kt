package br.espartano.sampleaccessibilitylistselectable

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class TeamsListViewModel: ViewModel() {

    private var lastSeletedTeam: Team? = null
    private var lastSelectedPosition: Int? = null

    private val _teamSeletedState = MutableLiveData<Team>()
    val updatedItem: LiveData<Pair<Int, Team>> = Transformations.map(
        _teamSeletedState,
        this::transformItem)

    private fun transformItem(team: Team): Pair<Int, Team> {
        val position = lastSelectedPosition ?: -1
        return Pair(position, team)
    }

    fun updateSeletedTeam(team: Team, position: Int) {
        lastSelectedPosition?.let {
            if (!team.selected) return

            if (it != position) {
                lastSeletedTeam?.let {  team ->
                    team.selected = false
                    _teamSeletedState.value = team
                }
            }
        }
        lastSeletedTeam = team
        lastSelectedPosition = position
    }

}