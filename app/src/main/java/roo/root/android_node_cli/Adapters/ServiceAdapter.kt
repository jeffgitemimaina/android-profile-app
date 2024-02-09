package roo.root.android_node_cli.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import roo.root.android_node_cli.Data.Service
import roo.root.android_node_cli.LandingFragmentDirections
import roo.root.android_node_cli.databinding.ServiceCardLayoutBinding

class ServiceAdapter(private val navController: NavController) : ListAdapter<Service, ServiceAdapter.ViewHolder>(ServiceDiffCallback()) {

    inner class ViewHolder(val binding: ServiceCardLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val serviceTitleTextView = binding.serviceTitleTextView
        val serviceDescriptionTextView = binding.serviceDescriptionTextView
        init {
            binding.root.setOnClickListener {
                val service = getItem(adapterPosition)
                val action = LandingFragmentDirections.actionLandingFragmentToMainActivity2()
                navController.navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ServiceCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val service = getItem(position)
        holder.apply {
            serviceTitleTextView.text = service.name
            serviceDescriptionTextView.text = service.description
        }
    }
}

class ServiceDiffCallback : DiffUtil.ItemCallback<Service>() {

    override fun areItemsTheSame(oldItem: Service, newItem: Service): Boolean {
        // Implement logic to check if items are the same.
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Service, newItem: Service): Boolean {
        // Implement logic to check if item contents are the same.
        return oldItem == newItem
    }
}
