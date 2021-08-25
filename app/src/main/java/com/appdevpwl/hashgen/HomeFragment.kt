package com.appdevpwl.hashgen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.appdevpwl.hashgen.databinding.FragmentHomeBinding


class HomeFragment : Fragment(), HashMethodsAdapter.OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvAdapter: HashMethodsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener{
            navigateToResult()
        }

        val hashMethodsArray  = resources.getStringArray(R.array.hash_methods)
        rvAdapter = HashMethodsAdapter(hashMethodsArray, this )
        binding.rvHashMethods.adapter = rvAdapter
        binding.rvHashMethods.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        return binding.root
    }

    private fun navigateToResult() {
        findNavController().navigate(R.id.action_homeFragment_to_resultFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
       Toast.makeText(activity?.applicationContext, position.toString(), Toast.LENGTH_SHORT).show()
    }


}