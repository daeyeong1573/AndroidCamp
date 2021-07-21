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
import org.arr.software.androidcamp.databinding.FirstFragmentblogBinding
import org.arr.software.androidcamp.viewmodel.FirstBlogViewModel

class FirstFragmentBlog : Fragment() {
    private lateinit var binding: FirstFragmentblogBinding

    private val viewModel: FirstBlogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FirstFragmentblogBinding.inflate(inflater).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    fun Fragment.findNavController(): NavController =
        NavHostFragment.findNavController(this)

}