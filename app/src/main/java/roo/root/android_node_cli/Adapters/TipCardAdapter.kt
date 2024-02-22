import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
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

        val spinnerAdapter = ArrayAdapter.createFromResource(holder.itemView.context, R.array.tip_options, android.R.layout.simple_spinner_item)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.tipSelectionSpinner.adapter = spinnerAdapter

        holder.tipSelectionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedTip = holder.tipSelectionSpinner.selectedItem.toString().toInt()
                val totalAmount = amount * selectedTip
                holder.tipAmountTextView.text = "Tip Amount: $totalAmount" // Update the text here
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Set selected tip to 1 if nothing is selected
                holder.tipSelectionSpinner.setSelection(0)
            }
        }

        holder.payButton.setOnClickListener {
            // Move pay button logic here if needed
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
