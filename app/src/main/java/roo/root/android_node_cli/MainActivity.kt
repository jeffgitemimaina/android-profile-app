package roo.root.android_node_cli

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager = findViewById(R.id.viewPager)
        val tabs: TabLayout = findViewById(R.id.tabs)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }
}
