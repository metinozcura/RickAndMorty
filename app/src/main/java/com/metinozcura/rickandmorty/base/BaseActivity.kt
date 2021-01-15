package com.metinozcura.rickandmorty.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    private lateinit var viewModel: VM
    private lateinit var binding: DB

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun getVM(): VM

    abstract fun bindVM(binding: DB, vm: VM)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = getVM()
        bindVM(binding, viewModel)
    }
}