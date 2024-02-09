package roo.root.android_node_cli

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import roo.root.android_node_cli.Adapters.ServiceAdapter
import roo.root.android_node_cli.Data.Service
import roo.root.android_node_cli.R

class LandingFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var serviceAdapter: ServiceAdapter
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_landing, container, false)

        navController = findNavController()

        recyclerView = view.findViewById(R.id.servicesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        serviceAdapter = ServiceAdapter(navController) // Pass the NavController to the adapter
        recyclerView.adapter = serviceAdapter

        // Fetch sample services in the background using coroutines
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val services = withContext(Dispatchers.IO) {
                    // Simulate fetching data from a database or network
                    fetchSampleServices()
                }
                // Update RecyclerView with the fetched services
                serviceAdapter.submitList(services)
            } catch (e: Exception) {
                Log.e("LandingFragment", "Error fetching services", e)
                // Handle error
            }
        }

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()

    }


    private suspend fun fetchSampleServices(): List<Service> {
        // Simulate fetching data from a database or network
        return listOf(
            Service("Java", "Utilized for backend development, including building RESTful APIs and managing server-side logic. Proficient in frameworks like Spring Boot and Hibernate for efficient development."),
            Service("Kotlin", "Used for Android app development, ensuring high performance and seamless user experiences. Skilled in building native Android apps and integrating with backend services."),
            Service("JavaScript", "Employed for both frontend and backend development, enabling dynamic and interactive web applications. Proficient in frameworks like React.js and Node.js for building modern web apps."),
            Service("HTML & CSS", "Used for crafting responsive and visually appealing user interfaces. Proficient in utilizing HTML5 and CSS3 features and frameworks like Bootstrap for efficient design."),
            Service("SQL", "Employed for database management and data manipulation. Skilled in writing complex queries, optimizing database performance, and ensuring data integrity."),
            Service("NoSQL", "Utilized for handling unstructured and semi-structured data. Proficient in using MongoDB for flexible and scalable data storage in web applications.")
        )
    }
}
