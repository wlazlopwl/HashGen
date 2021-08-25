package com.appdevpwl.hashgen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.appdevpwl.hashgen.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment(), HashMethodsAdapter.OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val hashViewModel: HashViewModel by viewModels()

    private lateinit var rvAdapter: HashMethodsAdapter
    private var checkedHashPosition: Int? = null
    lateinit var hashMethodsArray: Array<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            generateHash()
        }

        hashMethodsArray = resources.getStringArray(R.array.hash_methods)
        rvAdapter = HashMethodsAdapter(hashMethodsArray, this)
        binding.rvHashMethods.adapter = rvAdapter
        binding.rvHashMethods.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        return binding.root
    }

    private fun generateHash() {
        if (checkedHashPosition == null) showSnackBar("Select hash method")
        else if (binding.tvEnteredText.text.isEmpty()) showSnackBar("Enter text to hash")
        else {
            var textToHash = binding.tvEnteredText.text.toString()
            var hash = makeHash(checkedHashPosition!!, textToHash)
            navigateToResult(hash)
        }
    }

    private fun makeHash(checkedHashPosition: Int, textToHash: String): String {
        return hashViewModel.generateHash(hashMethodsArray[checkedHashPosition], textToHash)

    }

    private fun showSnackBar(s: String) {
        Snackbar.make(binding.root, s, Snackbar.LENGTH_SHORT).show()
    }


    private fun navigateToResult(hash: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToResultFragment(hash)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
        checkedHashPosition = position
    }


}