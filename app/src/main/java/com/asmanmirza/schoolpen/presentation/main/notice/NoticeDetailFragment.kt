package com.asmanmirza.schoolpen.presentation.main.notice

import android.annotation.SuppressLint
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.asmanmirza.schoolpen.databinding.FragmentNoticeDetailBinding
import com.bumptech.glide.Glide
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class NoticeDetailFragment : Fragment() {

    private var _binding:FragmentNoticeDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNoticeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }

            noticeTitle.text = arguments?.getString("title");

            val type = arguments?.getString("type")?.lowercase()
            if(type == "text"){
                noticeText.visibility = View.VISIBLE
                btnDownload.visibility = View.GONE
                noticeText.text = arguments?.getString("description")
            }else if(type == "video"){
                videoLayout.visibility = View.VISIBLE
                val mediaController = MediaController(requireContext())
                mediaController.setAnchorView(noticeVideo)
                mediaController.setMediaPlayer(noticeVideo)
                noticeVideo.setMediaController(mediaController)
                noticeVideo.setVideoURI(Uri.parse(arguments?.getString("file")))
                noticeVideo.start()
            }else if(type == "image"){
                noticeImage.visibility = View.VISIBLE
                Glide.with(requireContext()).load(arguments?.getString("file")).thumbnail(0.8f).into(noticeImage);
            }else if(type == "pdf"){
                noticePdf.visibility = View.VISIBLE
            }else{
                //nothing
            }

        }
    }

}