package roo.root.android_node_cli.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import roo.root.android_node_cli.Data.Service
import roo.root.android_node_cli.R


class ServiceAdapter(private val services: List<Service>) : RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    inner class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.serviceTitleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.serviceDescriptionTextView)
        val viewProjectsButton: Button = itemView.findViewById(R.id.viewProjectsButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.service_card_layout, parent, false)
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = services[position]
        holder.titleTextView.text = service.title
        holder.descriptionTextView.text = service.description
        holder.viewProjectsButton.setOnClickListener {
            // Handle button click here
        }
    }

    override fun getItemCount(): Int {
        return services.size
    }
}
