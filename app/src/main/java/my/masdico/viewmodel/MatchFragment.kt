package my.masdico.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import my.masdico.viewmodel.databinding.FragmentMatchBinding

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
        const val MATCH_CHOSEN = "match_chosen"
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
        sharedViewModel.changeTitle("Today's Match")
        activity?.title = sharedViewModel.displayTitle

        if (arguments != null) {
            when (arguments?.getInt(MATCH_CHOSEN)) {
                0 -> {
                    homeTeam = "Garuda.1"
                    allyTeam = "Chicago.1"
                }
                1 -> {
                    homeTeam = "Garuda.2"
                    allyTeam = "Lakers.2"
                }
                2 -> {
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
            binding.tvMatchTitle.text = getString(R.string.txt_match_title, arguments?.getString(TEAM_NAME))
            binding.tvLeftTeamname.text = arguments?.getString(TEAM_NAME)
            val position = arguments?.getInt(MATCH_CHOSEN)!!
            Glide.with(this)
                .load(BasketballTeamData.listTeam[position].imgLogo)
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