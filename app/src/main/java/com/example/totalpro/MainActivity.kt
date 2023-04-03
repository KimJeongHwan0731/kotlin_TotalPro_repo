package com.example.totalpro

import android.app.ProgressDialog.show
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.viewpager2.widget.ViewPager2
import com.example.totalpro.databinding.ActivityMainBinding
import com.example.totalpro.databinding.TabButtonBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var customAdapter: CustomAdapter
    lateinit var customMainAdapter: CustomMainAdapter
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customAdapter = CustomAdapter(this)
        customAdapter.addListFragment(MainFragment())
        customAdapter.addListFragment(MenuFragment())
        customAdapter.addListFragment(SearchFragment())
        binding.viewpager2.adapter = customAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewpager2) { tab, position ->
            tab.setCustomView(tabCustomView(position))
        }.attach()

        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.drawer_open,
            R.string.drawer_close
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        binding.navigationView.setNavigationItemSelectedListener(object :
            NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.item_salesdetails -> {
                        Toast.makeText(
                            applicationContext,
                            "이건 나중에",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                    R.id.item_purchasedetails -> Toast.makeText(
                        applicationContext,
                        "이것도 나중에",
                        Toast.LENGTH_SHORT
                    ).show()
                    R.id.item_logout -> Toast.makeText(
                        applicationContext,
                        "이것 또한 나중에",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                return true
            }
        })

        // ActionBar에 해당 페이지의 타이틀을 표시하는 코드
        binding.viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        supportActionBar?.title = "홈"
                    }
                    1 -> {
                        supportActionBar?.title = "내 근처"
                    }
                    2 -> {
                        supportActionBar?.title = "채팅"
                    }
                }
            }
        })
    }

    // AppSplashActivity 실행 메소드
    fun AppSplashStart(view: View) {
        val intent = Intent(this, AppSplashActivity::class.java)
        startActivity(intent)
    }

    fun tabCustomView(position: Int): View {
        val binding = TabButtonBinding.inflate(layoutInflater)
        when (position) {
            0 -> binding.ivIcon.setImageResource(R.drawable.home)
            1 -> binding.ivIcon.setImageResource(R.drawable.locate)
            2 -> binding.ivIcon.setImageResource(R.drawable.chat)
        }
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navi_menu, menu)
        val searchMenuItem = menu?.findItem(R.id.menu_search)
        val searchView = searchMenuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    customMainAdapter.filter(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    customMainAdapter.filter(newText)
                }
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}