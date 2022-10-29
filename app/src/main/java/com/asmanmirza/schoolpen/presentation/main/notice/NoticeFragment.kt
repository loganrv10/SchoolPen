package com.asmanmirza.schoolpen.presentation.main.notice

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.PopupWindow
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentHomeBinding
import com.asmanmirza.schoolpen.databinding.FragmentNoticeBinding
import com.asmanmirza.schoolpen.di.ItemClickSupport
import com.asmanmirza.schoolpen.di.ItemClickSupport.OnItemClickListener
import com.asmanmirza.schoolpen.presentation.main.host.HostFragment
import com.domonz.schoolpen_aie.Adapters.AdapterNotice
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeFragment : Fragment() {
    private var _binding: FragmentNoticeBinding? = null
    private val binding get() = _binding!!
    lateinit var data: ArrayList<ModelNotice>;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoticeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            HostFragment.instance.hideBottomNavBar(1)
            HostFragment.instance.hideDp(true)
            HostFragment.instance.hideTopButtons(true)
            recNotices.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addData()
            btnFilter.setOnClickListener {
                showFilterPopup()
            }
            btnSortBy.setOnClickListener {
                showSortPopup()
            }

            ItemClickSupport.addTo(recNotices).setOnItemClickListener { recyclerView, position, v ->
                val b = Bundle();
                b.putString("title", data[position].title)
                b.putString("description", data[position].description)
                b.putString("file", data[position].file)
                b.putString("type", data[position].type)
                findNavController().navigate(R.id.action_noticeFragment_to_noticeDetailFragment, b)
            }
        }
    }
    private fun addData() {
        data = ArrayList();
        data.add(
            ModelNotice(
                "Admission Notice – 2022-23 Class-XI (Class Eleven Only)",
                "19/07/2022",
                "",
                "https://cdn.pixabay.com/photo/2020/05/31/16/53/bookmarks-5243253_960_720.jpg",
                "image"
            )
        )
        data.add(
            ModelNotice(
                "Annual college Meeting video after award ceremony",
                "10/07/2022",
                "",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
                "video"
            )
        )

        data.add(
            ModelNotice(
                "Semester Exam notice",
                "10/07/2022",
                "",
                "https://www.orimi.com/pdf-test.pdf",
                "pdf"
            )
        )
        data.add(
            ModelNotice(
                "Avant garde competition announcement",
                "3/07/2022",
                "Did you know that globally nearly 1 in 4 girls ages 15–19 are not in school? These numbers (reported by UNICEF) tell a story of inequality. While a quarter of teen girls are without economic, academic, and professional pathways, only one tenth of boys face the same barriers to opportunity. Gender-based disadvantage greatly impacts transgender and nonbinary young people around the globe as well—in some places, female, trans, and nonbinary youth are ",
                "",
                "text"
            )
        )

        binding.recNotices.adapter = AdapterNotice(requireContext(), data);
    }

    private fun showFilterPopup() {
        val popup = PopupWindow(context)
        val layout: View = layoutInflater.inflate(R.layout.popup_filter_notice, null)
        popup.contentView = layout
        // Set content width and height
        // Set content width and height
        popup.height = WindowManager.LayoutParams.WRAP_CONTENT
        popup.width = WindowManager.LayoutParams.MATCH_PARENT
        popup.animationStyle = R.style.Animation


        popup.isOutsideTouchable = true
        popup.isFocusable = true
        // Show anchored to button
        // Show anchored to button
        popup.setBackgroundDrawable(BitmapDrawable())
        popup.showAtLocation(
            binding.recNotices, Gravity.BOTTOM, 0,
            0
        )
        popup.dimBehind()
    }

    private fun showSortPopup() {
        val popup = PopupWindow(context)
        val layout: View = layoutInflater.inflate(R.layout.popup_sort_by, null)
        popup.contentView = layout
        // Set content width and height
        // Set content width and height
        popup.height = WindowManager.LayoutParams.WRAP_CONTENT
        popup.width = WindowManager.LayoutParams.MATCH_PARENT
        popup.animationStyle = R.style.Animation


        popup.isOutsideTouchable = true
        popup.isFocusable = true
        // Show anchored to button
        // Show anchored to button
        popup.setBackgroundDrawable(BitmapDrawable())
        popup.showAtLocation(
            binding.recNotices, Gravity.BOTTOM, 0,
            0
        )
        popup.dimBehind()
    }

    fun PopupWindow.dimBehind() {
        val container = contentView.rootView
        val context = contentView.context
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val p = container.layoutParams as WindowManager.LayoutParams
        p.flags = p.flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND
        p.dimAmount = 0.3f
        wm.updateViewLayout(container, p)
    }

}