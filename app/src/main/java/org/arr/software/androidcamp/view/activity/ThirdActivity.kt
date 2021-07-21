package org.arr.software.androidcamp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import org.arr.software.androidcamp.R
import org.arr.software.androidcamp.databinding.ThirdActivityBinding
import org.arr.software.androidcamp.viewmodel.ThirdViewModel

class ThirdActivity : AppCompatActivity() {

    private lateinit var mBinding: ThirdActivityBinding
    private val model: ThirdViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.third_activity)

        mBinding.lifecycleOwner = this
        mBinding.viewModel = model

    }
}