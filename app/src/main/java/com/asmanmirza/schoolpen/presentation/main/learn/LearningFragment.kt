package com.asmanmirza.schoolpen.presentation.main.learn

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentLearningBinding
import com.asmanmirza.schoolpen.di.ItemClickSupport
import com.asmanmirza.schoolpen.di.ItemClickSupport.OnItemClickListener
import com.asmanmirza.schoolpen.presentation.main.host.HostFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class LearningFragment : Fragment(), View.OnTouchListener {

    private var _binding: FragmentLearningBinding? = null
    private val binding get() = _binding!!
    lateinit var adapterLearnButtons: AdapterLearnButtons;
    var dX = 0f
    var dY = 0f
    private var lastAction = 0
    var firstTouch = false;
    var default_dx = 0f;
    var default_dy = 0f


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLearningBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            HostFragment.instance.hideBottomNavBar(0)
            HostFragment.instance.hideDp(true)
            HostFragment.instance.hideTopButtons(true)
            HostFragment.instance.hideMenuIcon(true)
            HostFragment.instance.setNavBarColor("#00000000", "#259163D7")
            recLearnButtons.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
            adapterLearnButtons = AdapterLearnButtons(requireContext(), getButtons());
            recLearnButtons.adapter = adapterLearnButtons
            binding.floatingButton.setOnClickListener {
                findNavController().navigate(R.id.action_learnFragment_to_addQuestionFragment)
            }
            getData(0)
            getChallenges()

            ItemClickSupport.addTo(recLearnButtons).setOnItemClickListener(object:OnItemClickListener{
                @SuppressLint("NotifyDataSetChanged")
                override fun onItemClicked(recyclerView: RecyclerView?, position: Int, v: View?) {
                    adapterLearnButtons.selected = position;
                    adapterLearnButtons.notifyDataSetChanged()
                    getData(position)
                }

            })

            Glide.with(requireContext()).load("https://i.pravatar.cc/300").thumbnail(0.8f).diskCacheStrategy(
                DiskCacheStrategy.NONE).into(imageWinner)
        }

    }

    fun getData(pos:Int){

        if(pos == 0){
            binding.layoutChallenges.visibility = View.VISIBLE
            getChallenges()
        }else{
            binding.layoutChallenges.visibility = View.GONE
        }
        if(pos == 1){
            binding.layoutFeeds.visibility = View.VISIBLE
            getFeeds()
        }else{
            binding.layoutFeeds.visibility = View.GONE
        }

        if(pos == 2){
            binding.layoutWorldKnowledge.visibility = View.VISIBLE
            getWorldKnowledge()
        }else{
            binding.layoutWorldKnowledge.visibility = View.GONE
        }
    }

    fun getChallenges(){
        binding.recLeaderboard.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recLeaderboard.adapter = AdapterLeaderboardProfiles(requireContext(), getProfiles())
        binding.floatingButton.visibility = View.GONE
    }

    fun getFeeds(){
        binding.recFeeds.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recFeeds.adapter = AdapterFeeds(requireContext(), ArrayList())
        binding.floatingButton.visibility = View.VISIBLE
    }

    fun getWorldKnowledge(){
        binding.recWorldKnowledge.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recWorldKnowledge.adapter = AdapterWorldKnowledge(requireContext(), ArrayList())
        binding.floatingButton.visibility = View.GONE

    }

    fun getProfiles():ArrayList<ModelLeaderBoard>{

        return ArrayList<ModelLeaderBoard>().apply {

            add(ModelLeaderBoard("", "Jain Edward", "98.7%", ""))
            add(ModelLeaderBoard("", "Jahan Singh", "98.1%", ""))
            add(ModelLeaderBoard("", "Sonu Sharma", "98%", ""))
            add(ModelLeaderBoard("", "Sujata Mathur", "97.5%", ""))

        }

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, event: MotionEvent): Boolean {

        when (event.actionMasked) {

            MotionEvent.ACTION_DOWN -> {
                dX = view.x - event.rawX
                dY = view.y - event.rawY
                lastAction = MotionEvent.ACTION_DOWN
                Toast.makeText(requireContext(), "down", Toast.LENGTH_SHORT).show()
            }
            MotionEvent.ACTION_MOVE -> {
                view.y = event.rawY + dY
                view.x = event.rawX + dX
                lastAction = MotionEvent.ACTION_MOVE
                if (!firstTouch) {
                    firstTouch = true;
                    default_dx = view.x
                    default_dy = view.y
                }
                Toast.makeText(requireContext(), "move", Toast.LENGTH_SHORT).show()
            }
            MotionEvent.ACTION_UP -> {
                if (lastAction == MotionEvent.ACTION_DOWN) {
                    findNavController().navigate(R.id.action_learnFragment_to_addQuestionFragment)
                } /*else if (lastAction === MotionEvent.ACTION_MOVE) {
                    binding.floatingButton.x = default_dx;
                    binding.floatingButton.y = default_dy
                }*/else{
                    return false
                }
            }

        }
        return true
    }



    fun getButtons():ArrayList<ModelLearnButtons>{

        return ArrayList<ModelLearnButtons>().apply {
            add(ModelLearnButtons(R.drawable.challanges_vector, "Challenges"))
            add(ModelLearnButtons(R.drawable.feeds_vector, "Feeds"))
            add(ModelLearnButtons(R.drawable.world_knowledge, "World Knowledge"))
        }

    }

}