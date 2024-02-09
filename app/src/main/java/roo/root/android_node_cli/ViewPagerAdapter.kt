package roo.root.android_node_cli


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

// Import other fragments if needed

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> LandingFragment() // LandingFragment as the first tab
            5-> BuycoffeeFragment()
            // Add other fragments for other tabs if needed
            else -> LandingFragment() // Default to LandingFragment
        }
    }

    override fun getCount(): Int {
        return 6 // Total number of tabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Gitemijeff Devs" // Tab title for LandingFragment
            1 -> "Skills" // Tab title for SkillsFragment
            2 -> "Technoverse" // Tab title for TechnoverseFragment
            3 -> "Contact" // Tab title for ContactFragment
            4 -> "Recent Works" // Tab title for RecentWorksFragment
            5 -> "Support the Developer" // Tab title for SupportFragment
            else -> ""
        }
    }
}
