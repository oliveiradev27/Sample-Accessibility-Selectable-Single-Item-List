package br.espartano.sampleaccessibilitylistselectable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.espartano.sampleaccessibilitylistselectable.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: TeamsListViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(TeamsListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView = binding.recyclerTeams

        viewModel.updatedItem.observe(this, {
            val (position, team) = it
            recyclerView.adapter?.notifyItemChanged(position, team)
        })

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = TeamsAdapter(TeamsDataSource.teams) { selectedTeam, selectedPosition ->
               viewModel.updateSeletedTeam(selectedTeam, selectedPosition)
            }
            setHasFixedSize(true)
        }
    }
}