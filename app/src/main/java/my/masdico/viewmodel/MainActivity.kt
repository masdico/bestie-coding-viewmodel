package my.masdico.viewmodel

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import my.masdico.viewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var rvBasketballTeam: RecyclerView
    private var listBasketballTeam = arrayListOf<BasketballTeam>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        rvBasketballTeam = mainBinding.rvMatch
        rvBasketballTeam.setHasFixedSize(true)
        listBasketballTeam.addAll(BasketballTeamData.listTeam)
        showBasketballTeam()
    }

    private fun showBasketballTeam() {
        rvBasketballTeam.layoutManager = LinearLayoutManager(this)
        val adapter = ListMatchAdapter(listBasketballTeam)
        rvBasketballTeam.adapter = adapter
        adapter.setOnItemClickListener(object : ListMatchAdapter.OnItemClickListener {
            override fun onItemClicked(position: Int) {
                Toast.makeText(this@MainActivity, "This is the match of "+BasketballTeamData.listTeam[position].name, Toast.LENGTH_LONG).show()
            }
        })

        adapter.setOnButtonClickListener(object : ListMatchAdapter.OnItemClickListener {
            override fun onItemClicked(position: Int) {
                val bundle = Bundle()
                bundle.putString(MatchActivity.TEAM_NAME, BasketballTeamData.listTeam[position].name)
                bundle.putInt(MatchActivity.TEAM_ID, position)
                val intent = Intent(this@MainActivity, MatchActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })

        supportActionBar?.title = mainViewModel.displayTitle
    }
}