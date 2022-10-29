package com.asmanmirza.schoolpen.presentation.main.live

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentLiveClassDetailBinding
import com.asmanmirza.schoolpen.databinding.FragmentLiveClassesBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class LiveClassDetailFragment : Fragment() {

    private var _binding: FragmentLiveClassDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLiveClassDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            videoView.visibility = View.INVISIBLE
            val url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"
            val mediaController = MediaController(requireContext())
            mediaController.setAnchorView(videoView)
            mediaController.setMediaPlayer(videoView)
            videoView.setMediaController(mediaController)
            videoView.setVideoURI(Uri.parse(url))
            Glide.with(requireContext()).load(url).thumbnail(0.8f).diskCacheStrategy(DiskCacheStrategy.ALL).into(thumbnailVideo)

            btnPlay.setOnClickListener {
                thumbnailVideo.visibility = View.GONE
                btnPlay.visibility = View.GONE
                videoView.start()
                videoView.visibility = View.VISIBLE
            }

            videoView.setOnCompletionListener {
                thumbnailVideo.visibility = View.VISIBLE
                btnPlay.visibility = View.VISIBLE
                videoView.visibility = View.INVISIBLE
            }

        }

    }

}