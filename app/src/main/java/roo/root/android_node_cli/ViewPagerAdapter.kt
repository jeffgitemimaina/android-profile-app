package roo.root.android_node_cli

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> LandingFragment()
           // 1 -> SkillsFragment()
         //  2 -> TechnoverseFragment()
         //   3 -> ContactFragment()
          //  4 -> RecentWorksFragment()
          //  5 -> SupportFragment()
            else -> LandingFragment()
        }
    }

    override fun getCount(): Int {
        return 6 // Total number of tabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Gitemijeff Devs"
            1 -> "Skills"
            2 -> "Technoverse"
            3 -> "Contact"
            4 -> "Recent Works"
            5 -> "Support the Developer"
            else -> ""
        }
    }
}
