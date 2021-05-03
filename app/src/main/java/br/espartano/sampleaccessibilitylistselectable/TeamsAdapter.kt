package br.espartano.sampleaccessibilitylistselectable

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.espartano.sampleaccessibilitylistselectable.databinding.ItemRadioTeamsBinding

class TeamsAdapter(
    private var teams: List<Team>,
    private val listener: (team: Team, position: Int) -> Unit
): RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder>() {

    var selectedTeam: Team? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val binding = ItemRadioTeamsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TeamsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        val team = teams[position]
        if (team.selected) {
            selectedTeam = team
        }
        holder.bind(teams[position], position)
    }

    private  fun updateSelectedItem(team: Team, position: Int) {
        listener(team, position)
    }

    override fun getItemCount(): Int = teams.size

    inner class TeamsViewHolder(private val binding: ItemRadioTeamsBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(team: Team, position: Int) {
            val (name, selected) = team
            binding.radioTeam.apply {
                text = name
                isChecked = selected

                setOnCheckedChangeListener { _, isChecked ->
                    team.selected = isChecked
                    listener(team, position)
                }
            }
        }
    }
}