package com.aariz.dxbapps

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aariz.dxbapps.databinding.ActivityMainBinding
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        //bottom nav top border radius
        val radius = resources.getDimension(R.dimen.radius_small)
        val bottomNavigationViewBackground = binding.bottomNavView.background as MaterialShapeDrawable
        bottomNavigationViewBackground.shapeAppearanceModel =
            bottomNavigationViewBackground.shapeAppearanceModel.toBuilder()
                .setTopRightCorner(CornerFamily.ROUNDED, radius)
                .setTopLeftCorner(CornerFamily.ROUNDED, radius)
                .build()

        //status bar transparent
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
            window.statusBarColor = Color.TRANSPARENT
        }

        //replace fragments when bottom nav OnTab
        binding.bottomNavView.setOnItemSelectedListener {
            Log.d("kiki", "onCreate: " + it.itemId)
            when (it.itemId) {
                R.id.action_home -> replaceFragment(HomeFragment())
                R.id.action_orders -> replaceFragment(OrdersFragment())
                R.id.action_notification -> replaceFragment(NotificationsFragment())
                R.id.action_account -> replaceFragment(AccountFragment())
            }
            true
        }
        binding.fab.setOnClickListener {
            replaceFragment(NewOrderFragment())
        }
    }

    //Function to replace Fragment
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_container, fragment)
        fragmentTransaction.commit()
    }
}

