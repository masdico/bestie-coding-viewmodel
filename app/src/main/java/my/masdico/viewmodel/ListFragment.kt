package my.masdico.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import my.masdico.viewmodel.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvBasketballTeam: RecyclerView
    private var listBasketballTeam = arrayListOf<BasketballTeam>()
    private val sharedViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvBasketballTeam = binding.rvMatch

        showMatchList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showMatchList() {
        sharedViewModel.changeTitle("NBA Match")
        activity?.title = sharedViewModel.displayTitle

        rvBasketballTeam.setHasFixedSize(true)
        listBasketballTeam.removeAll(BasketballTeamData.listTeam.toSet())
        listBasketballTeam.addAll(BasketballTeamData.listTeam)
        rvBasketballTeam.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ListMatchAdapter(listBasketballTeam)
        rvBasketballTeam.adapter = adapter

        adapter.itemListener = object : ListMatchAdapter.OnItemClickListener {
            override fun onItemClicked(position: Int) {
                Toast.makeText(
                    requireContext(),
                    "This is the match of " + BasketballTeamData.listTeam[position].name,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        adapter.buttonListener = object : ListMatchAdapter.OnItemClickListener {
            override fun onItemClicked(position: Int) {

                val bundle = Bundle()
                val matchFragment = MatchFragment()
                bundle.putParcelable(MatchFragment.TEAM_NAME, BasketballTeamData.listTeam[position])
                matchFragment.arguments = bundle

                val fragmentManager = parentFragmentManager
                fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, matchFragment, MatchFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()
            }
        }


    }

}