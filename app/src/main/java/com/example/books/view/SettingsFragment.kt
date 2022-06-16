package com.example.books.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.books.R
import com.example.books.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)

        binding.nameUserSettings.setText("${getNameInSharedPreference()}")

        binding.saveUserName.setOnClickListener {
            setNameInSharedPreference(binding.nameUserSettings.text.toString())
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }



        return binding.root
    }

    private fun getNameInSharedPreference(): String? =
        requireActivity().getSharedPreferences("NAME", Context.MODE_PRIVATE).getString("name", "User")


    private fun setNameInSharedPreference(name: String){
        requireActivity().getSharedPreferences("NAME", Context.MODE_PRIVATE).edit().putString("name", name).commit()
    }

}