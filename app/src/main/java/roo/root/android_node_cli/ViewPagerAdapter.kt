package roo.root.android_node_cli


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter



class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> LandingFragment() // LandingFragment as the first tab
            1->LandingFragment()
            2 -> BuyCoffeeFragment()
            else -> LandingFragment() // Default to LandingFragment
        }
    }

    override fun getCount(): Int {
        return 3 // Total number of tabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Gitemijeff Devs" // Tab title for LandingFragment
            1 -> "Skills" // Tab title for SkillsFragment
            2 -> "coffee" // Tab title for SupportFragment
            else -> ""
        }
    }
}
