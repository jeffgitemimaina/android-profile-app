package roo.root.android_node_cli

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import roo.root.android_node_cli.databinding.FragmentContactBinding

class ContactFragment : Fragment() {
private lateinit var binding: FragmentContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {

        binding= FragmentContactBinding.inflate(layoutInflater)


        binding.btnRegister.setOnClickListener {
            var fname = binding.etFirstName.text.toString()
            var sname = binding.etSecondName.text.toString()
            var email = binding.etEmail.text.toString()
            var pnumber = binding.etPhoneNumber.text.toString()
            var pwd = binding.etPassword.text.toString()

            if (pnumber.length < 10){
                binding.etPhoneNumber.error = "please enter a valid kenyan  number "
            }

        }

        binding.btnRegister.setOnClickListener {

        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }



    override fun onDestroyView() {
        super.onDestroyView()

    }



}