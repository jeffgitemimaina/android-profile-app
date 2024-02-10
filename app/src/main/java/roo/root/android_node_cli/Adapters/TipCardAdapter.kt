import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import roo.root.android_node_cli.R

class TipCardAdapter(private val dataList: List<Int>) : RecyclerView.Adapter<TipCardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tip_card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val amount = dataList[position]

        holder.tipAmountTextView.text = "Tip Amount: $amount"

        val spinnerAdapter = ArrayAdapter.createFromResource(holder.itemView.context, R.array.tip_options, android.R.layout.simple_spinner_item)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.tipSelectionSpinner.adapter = spinnerAdapter

        holder.payButton.setOnClickListener {
            val selectedTip = holder.tipSelectionSpinner.selectedItem.toString().toInt()
            val totalAmount = amount * selectedTip
            holder.phoneNumberEditText.error = null
            if (holder.phoneNumberEditText.text.toString().length < 10) {
                holder.phoneNumberEditText.error = "Enter a valid Kenyan phone number"
            } else {
                // Perform payment logic here
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tipAmountTextView: TextView = itemView.findViewById(R.id.tipAmountTextView)
        val tipSelectionSpinner: Spinner = itemView.findViewById(R.id.tipSelectionSpinner)
        val phoneNumberEditText: EditText = itemView.findViewById(R.id.phoneNumberEditText)
        val payButton: Button = itemView.findViewById(R.id.payButton)
    }
}
