package com.appdevpwl.hashgen

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.appdevpwl.hashgen.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    private val args: ResultFragmentArgs? by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResultBinding.inflate(inflater, container, false)

        binding.tvHash.text = args?.hashString
        binding.btnCopy.setOnClickListener {
            copyToClipboard(args?.hashString.toString())
        }
        binding.btnSend.setOnClickListener {
            sendHash(args?.hashString.toString())
        }

        return binding.root
    }

    private fun sendHash(hash: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, hash)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)

    }

    private fun copyToClipboard(hash: String) {
        val clipboard =
            requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val copyText: ClipData = ClipData.newPlainText("hash", hash)
        clipboard.setPrimaryClip(copyText)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}