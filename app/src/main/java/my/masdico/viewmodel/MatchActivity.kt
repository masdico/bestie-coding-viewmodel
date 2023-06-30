package my.masdico.viewmodel

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import my.masdico.viewmodel.databinding.ActivityMatchBinding

class MatchActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var matchBinding: ActivityMatchBinding
    private lateinit var scoreTeamModel: MainViewModel
    companion object {
        const val TEAM_ID = "team_id"
        const val TEAM_NAME = "team_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        matchBinding = ActivityMatchBinding.inflate(layoutInflater)
        setContentView(matchBinding.root)
        scoreTeamModel = ViewModelProvider(this)[MainViewModel::class.java]

        showMatchScore()
        matchBinding.btnReset.setOnClickListener(this)
        matchBinding.btnLeftPlusthree.setOnClickListener(this)
        matchBinding.btnLeftPlustwo.setOnClickListener(this)
        matchBinding.btnRightPlusthree.setOnClickListener(this)
        matchBinding.btnRightPlustwo.setOnClickListener(this)
    }

    private fun showMatchScore() {
        val bundle = intent.extras
        matchBinding.tvMatchTitle.text = getString(R.string.txt_match_title, bundle?.getString(TEAM_NAME))
        matchBinding.tvLeftTeamname.text = bundle?.getString(TEAM_NAME)
        matchBinding.tvLeftScore.text = scoreTeamModel.scoreTeamA
        matchBinding.tvRightScore.text = scoreTeamModel.scoreTeamB
        scoreTeamModel.changeTitle("Today's Match")
        supportActionBar?.title = scoreTeamModel.displayTitle
        val position = bundle!!.getInt(TEAM_ID)
        Glide.with(this)
            .load(BasketballTeamData.listTeam[position].imgLogo)
            .apply(RequestOptions().override(170, 170))
            .into(matchBinding.imgMatchLogo)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_reset -> {
                Toast.makeText(this, "Reset Game", Toast.LENGTH_SHORT).show()
                scoreTeamModel.resetScore()
                matchBinding.tvLeftScore.text = scoreTeamModel.scoreTeamA
                matchBinding.tvRightScore.text = scoreTeamModel.scoreTeamB
            }
            R.id.btn_left_plusthree -> {
                scoreTeamModel.addScoreTeam(3,"A")
                matchBinding.tvLeftScore.text = scoreTeamModel.scoreTeamA
            }
            R.id.btn_left_plustwo -> {
                scoreTeamModel.addScoreTeam(2,"A")
                matchBinding.tvLeftScore.text = scoreTeamModel.scoreTeamA
            }
            R.id.btn_right_plusthree -> {
                scoreTeamModel.addScoreTeam(3,"B")
                matchBinding.tvRightScore.text = scoreTeamModel.scoreTeamB
            }
            R.id.btn_right_plustwo -> {
                scoreTeamModel.addScoreTeam(2,"B")
                matchBinding.tvRightScore.text = scoreTeamModel.scoreTeamB
            }
        }
    }
}