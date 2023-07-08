package my.masdico.viewmodel

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import my.masdico.viewmodel.databinding.FragmentMatchBinding

@Suppress("DEPRECATION")
class MatchFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentMatchBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedViewModel: MainViewModel
    /* shared ViewModel using delegation needs -> implementation 'androidx.fragment:fragment-ktx' in gradle(app)
      private val sharedViewModel: MainViewModel by activityViewModels()
    */
    private lateinit var homeTeam: String
    private lateinit var allyTeam: String

    companion object {
        const val TEAM_NAME = "team_name"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        binding.btnLeftPlusthree.setOnClickListener(this)
        binding.btnLeftPlustwo.setOnClickListener(this)
        binding.btnRightPlusthree.setOnClickListener(this)
        binding.btnRightPlustwo.setOnClickListener(this)
        binding.btnReset.setOnClickListener(this)
        showMatch()
    }

    private fun showMatch() {
        homeTeam = "home"
        allyTeam = "ally"
        sharedViewModel.changeTitle("Today's Match")
        activity?.title = sharedViewModel.displayTitle
        val matchChosen = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            arguments?.getParcelable(TEAM_NAME, BasketballTeam::class.java)
        } else {
            arguments?.getParcelable(TEAM_NAME)
        }

        if (matchChosen != null) {
            when (matchChosen.imgLogo) {
                R.drawable.nba_chicago_bulls -> {
                    homeTeam = "Garuda.1"
                    allyTeam = "Chicago.1"
                }
                R.drawable.nba_la_lakers -> {
                    homeTeam = "Garuda.2"
                    allyTeam = "Lakers.2"
                }
                R.drawable.nba_boston_celtics -> {
                    homeTeam = "Garuda.3"
                    allyTeam = "Celtics.3"
                }
            }
            if (sharedViewModel.score[homeTeam] != null && sharedViewModel.score[allyTeam] != null) {
                binding.tvLeftScore.text = sharedViewModel.score[allyTeam].toString()
                binding.tvRightScore.text = sharedViewModel.score[homeTeam].toString()
            } else {
                binding.tvLeftScore.text = "0"
                binding.tvRightScore.text = "0"
            }
            binding.tvMatchTitle.text = getString(R.string.txt_match_title, matchChosen.name)
            binding.tvLeftTeamname.text = matchChosen.name
            Glide.with(this)
                .load(matchChosen.imgLogo)
                .apply(RequestOptions().override(170, 170))
                .into(binding.imgMatchLogo)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.btn_left_plusthree -> {
                sharedViewModel.addScoreTeam(3, allyTeam)
                binding.tvLeftScore.text = sharedViewModel.score[allyTeam].toString()
            }
            R.id.btn_left_plustwo -> {
                sharedViewModel.addScoreTeam(2, allyTeam)
                binding.tvLeftScore.text = sharedViewModel.score[allyTeam].toString()
            }
            R.id.btn_right_plusthree -> {
                sharedViewModel.addScoreTeam(3, homeTeam)
                binding.tvRightScore.text = sharedViewModel.score[homeTeam].toString()
            }
            R.id.btn_right_plustwo -> {
                sharedViewModel.addScoreTeam(2, homeTeam)
                binding.tvRightScore.text = sharedViewModel.score[homeTeam].toString()
            }
            R.id.btn_reset -> {
                sharedViewModel.resetScore(homeTeam, allyTeam)
                binding.tvLeftScore.text = "0"
                binding.tvRightScore.text = "0"
            }
        }
    }
}