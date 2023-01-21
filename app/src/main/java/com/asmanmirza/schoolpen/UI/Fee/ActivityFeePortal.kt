package com.asmanmirza.schoolpen.UI.Fee

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Fee.Adapter.PaymentHistoryAdapter
import com.asmanmirza.schoolpen.databinding.ActivityFeePortalBinding

class ActivityFeePortal : AppCompatActivity() {
    private var _binding: ActivityFeePortalBinding? = null
    private val binding get() = _binding
    lateinit var adapter: PaymentHistoryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFeePortalBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        openFragment(FeePortalFragment())

    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}