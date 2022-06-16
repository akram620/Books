package com.example.books.view


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.books.viewmodel.BooksViewModel
import com.example.books.R
import com.example.books.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var booksViewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setColors()
        replaceFragment(HomeFragment())


        booksViewModel = ViewModelProvider(this)[BooksViewModel::class.java]


        binding.home.setOnClickListener{
            homeClick()
            replaceFragment(HomeFragment())
        }

        binding.favorite.setOnClickListener{
            favoriteClick()
            replaceFragment(FavoriteFragment())
        }

        binding.market.setOnClickListener{
            marketClick()
            replaceFragment(MarketFragment())
        }

        binding.settings.setOnClickListener{
            settingsClick()
            replaceFragment(SettingsFragment())
        }

    }


    public fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }


    private fun homeClick(){
        binding.home.setImageResource(R.drawable.ic_home_1)
        binding.favorite.setImageResource(R.drawable.ic_favorite_0)
        binding.market.setImageResource(R.drawable.ic_market_0)
        binding.settings.setImageResource(R.drawable.ic_settings_0)
    }

    private fun favoriteClick(){
        binding.home.setImageResource(R.drawable.ic_home_0)
        binding.favorite.setImageResource(R.drawable.ic_favorite_1)
        binding.market.setImageResource(R.drawable.ic_market_0)
        binding.settings.setImageResource(R.drawable.ic_settings_0)
    }

    private fun marketClick(){
        binding.home.setImageResource(R.drawable.ic_home_0)
        binding.favorite.setImageResource(R.drawable.ic_favorite_0)
        binding.market.setImageResource(R.drawable.ic_market_1)
        binding.settings.setImageResource(R.drawable.ic_settings_0)
    }

    private fun settingsClick(){
        binding.home.setImageResource(R.drawable.ic_home_0)
        binding.favorite.setImageResource(R.drawable.ic_favorite_0)
        binding.market.setImageResource(R.drawable.ic_market_0)
        binding.settings.setImageResource(R.drawable.ic_settings_1)
    }






    private fun setColors(){
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;//  set status text dark
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        window.navigationBarColor = this.resources.getColor(android.R.color.white)
    }
}