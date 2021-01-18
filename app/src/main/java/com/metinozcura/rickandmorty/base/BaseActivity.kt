package com.metinozcura.rickandmorty.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.metinozcura.rickandmorty.ext.observe

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
        with(viewModel) {
            observe(progressLiveEvent) { show ->
                if (show) showProgress()
                else hideProgress()
            }

            observe(errorMessage) { msg ->
                Toast.makeText(this@BaseActivity, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun showProgress() = BaseProgress().show(supportFragmentManager, PROGRESS)

    fun hideProgress() =
        supportFragmentManager.fragments.filterIsInstance<BaseProgress>().forEach { it.dismiss() }

    companion object {
        private const val PROGRESS = "Progress"
    }
}