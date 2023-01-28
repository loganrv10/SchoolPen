package com.asmanmirza.schoolpen.UI.Student.di

import com.asmanmirza.schoolpen.UI.Student.Fee.ActivityStudentProfile
import com.asmanmirza.schoolpen.UI.Teacher.Profile.ProfileTeacherActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: ActivityStudentProfile)

    fun inject(mainActivity: ProfileTeacherActivity)
}