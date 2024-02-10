// BuyCoffeeFragment.kt
package roo.root.android_node_cli
import TipCardAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class BuyCoffeeFragment : Fragment() {

    private val tipCards = listOf(50, 100, 200, 500, 1000, 2000, 3000, 5000, 10000)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_buycoffee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tipCardAdapter = TipCardAdapter(tipCards)
        val recyclerView = view.findViewById<RecyclerView>(R.id.tipCardsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = tipCardAdapter

    }
}
