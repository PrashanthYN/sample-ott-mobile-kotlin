package com.pyn.sample_ott_mobile.ui.view.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.pyn.sample_ott_mobile.R
import com.pyn.sample_ott_mobile.ui.view.fragments.ContentFragment
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        navigateToMovies()
        tabs.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0->navigateToMovies()
                    1->navigateToTvShows()
                }

            }

        })
    }

    private fun navigateToMovies() {
        val contentFragment=ContentFragment()
        val args = Bundle()
        args.putString("section", "movies")
        contentFragment.arguments=args
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerLayoutId,contentFragment).commit()
    }

    private fun navigateToTvShows() {
        val contentFragment=ContentFragment()
        val args = Bundle()
        args.putString("section", "tvshows")
        contentFragment.arguments=args
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerLayoutId,contentFragment).commit()
    }
}
