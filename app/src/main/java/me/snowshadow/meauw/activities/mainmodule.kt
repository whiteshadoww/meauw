package me.snowshadow.meauw.activities

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.snowshadow.meauw.fragments.*

@Module
interface MainActivityModule {

    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    fun activity(): MainActivity
}

@Module
interface MainFragmentModule {

    @ContributesAndroidInjector
    fun mainFrag(): MainFragment

    @ContributesAndroidInjector
    fun profileFrag(): ProfileFragment

    @ContributesAndroidInjector
    fun swipeFrag(): SwipeFragment

    @ContributesAndroidInjector
    fun topPickFrag(): TopPicksFragment

    @ContributesAndroidInjector
    fun updatesFrag(): UpdatesFragment

    @ContributesAndroidInjector
    fun centerFrag(): CenterFragment

    @ContributesAndroidInjector
    fun catListFrag(): CatListFragment
}
