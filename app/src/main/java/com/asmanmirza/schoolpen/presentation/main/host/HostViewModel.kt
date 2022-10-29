package com.asmanmirza.schoolpen.presentation.main.host

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: Asman Mirza
 * @Email: asman@otmalse.com
 * @Date: 01-10-2022
 * @Time: 06:40
 */
@HiltViewModel
class HostViewModel @Inject constructor() : ViewModel() {
    var user = ""
}