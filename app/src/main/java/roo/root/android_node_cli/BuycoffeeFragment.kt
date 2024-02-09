package roo.root.android_node_cli
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import roo.root.android_node_cli.R

class BuyCoffeeFragment : Fragment() {

    private lateinit var spinner: Spinner
    private lateinit var phoneNumberEditText: EditText
    private lateinit var payButton: Button
    private lateinit var tipAmountTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        spinner = view.findViewById(R.id.tipSelectionSpinner)
        tipAmountTextView = view.findViewById(R.id.tipAmountTextView)
        phoneNumberEditText = view.findViewById(R.id.phoneNumberEditText)
        payButton = view.findViewById(R.id.payButton)

        // Initialize Spinner
        val myTipSizeArray = arrayOf("1", "2", "3", "4", "5")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, myTipSizeArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Set onItemSelectedListener for the spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Update tip amount text view when spinner item is selected
                val tipAmount = myTipSizeArray[position].toInt() * 50 // Assuming each tip card value is 50
                tipAmountTextView.text = "Tip Amount: $tipAmount" // Update text view
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        // Set onClickListener for the Pay button
        payButton.setOnClickListener {
            // Validate Kenyan phone number
            if (isValidKenyanPhoneNumber()) {
                // Perform STK push
                performSTKPush()
            } else {
                Toast.makeText(requireContext(), "Invalid Kenyan phone number", Toast.LENGTH_SHORT).show()
            }
        }

        // Set default values for tip cards
        setDefaultTipValues()
    }

    private fun setDefaultTipValues() {
        val tipCardValues = arrayOf(50, 100, 200, 500, 1000, 2000, 3000, 5000, 10000)

        for (i in 1..tipCardValues.size) {
            val tipCardLayoutId = resources.getIdentifier("tipCardLayout$i", "id", activity?.packageName)
            val tipCardLayout = view?.findViewById<LinearLayout>(tipCardLayoutId)

            // Set default tip amount on each card
            val tipAmountTextView = tipCardLayout?.findViewById<TextView>(R.id.tipAmountTextView)
            tipAmountTextView?.text = "${tipCardValues[i - 1]} KES"
        }
    }

    private fun isValidKenyanPhoneNumber(): Boolean {
        val phoneNumber = phoneNumberEditText.text.toString().trim()
        // Add your phone number validation logic here
        // For simplicity, we assume a valid Kenyan phone number starts with  and has 10 digits
        return phoneNumber.matches(Regex("^\\d{10}$"))
    }

    private fun performSTKPush() {
        var totalAmount = 0 // Initialize total amount to zero

        // Calculate total amount for all tip cards
        for (i in 1..9) { // Assuming there are 9 tip cards
            val tipCardLayoutId = resources.getIdentifier("tipCardLayout$i", "id", activity?.packageName)
            val tipCardLayout = view?.findViewById<LinearLayout>(tipCardLayoutId)
            val tipAmountTextView = tipCardLayout?.findViewById<TextView>(R.id.tipAmountTextView)

            // Extract tip amount from tip card text view
            val tipAmountText = tipAmountTextView?.text.toString().replace(" KES", "")
            val tipAmount = tipAmountText.toIntOrNull() ?: 0 // Convert to integer or default to zero if conversion fails

            // Add tip amount to total
            totalAmount += tipAmount
        }

        val phoneNumber = phoneNumberEditText.text.toString().trim()

        // Construct JSON body for STK push
        val jsonBody = JSONObject().apply {
            put("businessShortCode", "174379")
            put("password", "{{mpesa_password}}")
            put("Timestamp", "20160216165627")
            put("transactionType", "CustomerPayBillOnline")
            put("amount", totalAmount)
            put("partyA", phoneNumber)
            put("partyB", "600000")
        }

        // Perform STK push asynchronously
        GlobalScope.launch(Dispatchers.IO) {
            // Make STK push request using Daraja API
            val response = makeSTKPush(jsonBody)

            // Handle response accordingly (show toast, update UI, etc.)
            activity?.runOnUiThread {
                if (response.isSuccess) {
                    Toast.makeText(requireContext(), "STK push successful!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "STK push failed!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun makeSTKPush(jsonBody: JSONObject): Response {
        // Implement your logic here to make STK push using Daraja API
        // Example: Call Daraja API endpoint and parse the response
        // For simplicity, we simulate a successful response here
        return Response(isSuccess = true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_buycoffee, container, false)
    }

    data class Response(val isSuccess: Boolean)
}
