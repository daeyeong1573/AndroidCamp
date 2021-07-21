package org.arr.software.androidcamp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import org.arr.software.androidcamp.R
import org.arr.software.androidcamp.databinding.SecondFragmentblogBinding
import org.arr.software.androidcamp.viewmodel.SecondBlogViewModel

class SecondFragmentBlog : Fragment() {

    private lateinit var binding: SecondFragmentblogBinding

    private val viewModel: SecondBlogViewModel by viewModels<SecondBlogViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return SecondFragmentblogBinding.inflate(inflater).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    fun Fragment.findNavController(): NavController =
        NavHostFragment.findNavController(this)

}