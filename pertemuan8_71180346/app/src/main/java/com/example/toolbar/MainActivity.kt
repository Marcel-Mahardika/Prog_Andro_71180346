package com.example.toolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    //inisialisasi variable global pager
    lateinit var pager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //untuk toolbar
        setSupportActionBar(findViewById(R.id.toolbar_default))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //untuk viewpager
        val listFragment = arrayListOf<Fragment>(FragmentStory(), FragmentFavorite(), FragmentMessage())
        pager = findViewById(R.id.pager)
        val pagerAdapter = PagerAdapter(this, listFragment)
        pager.adapter = pagerAdapter
    }

    class PagerAdapter(val activity: AppCompatActivity, val listFragment: ArrayList<Fragment>) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = listFragment.size
        override fun createFragment(position: Int): Fragment = listFragment.get(position)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_default, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem)= when(item.itemId) {
        R.id.menu_story -> {
            Toast.makeText(this,"Menu Story", Toast.LENGTH_SHORT).show()
            pager.setCurrentItem(0)
            true
        }
        R.id.menu_favorite -> {
            Toast.makeText(this,"Menu Favorite", Toast.LENGTH_SHORT).show()
            pager.setCurrentItem(1)
            true
        }
        R.id.menu_message -> {
            Toast.makeText(this,"Menu Message", Toast.LENGTH_SHORT).show()
            pager.setCurrentItem(2)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

}