package com.rahulverlekar.pokedex.base

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.rahulverlekar.pokedex.utils.BaseEvent
import com.rahulverlekar.pokedex.utils.ErrorEvent
import com.rahulverlekar.pokedex.utils.SuccessEvent
import com.rahulverlekar.pokedex.utils.ToastEvent

abstract class BaseActivity<out T : ViewDataBinding>(@LayoutRes val layoutId: Int) :
    AppCompatActivity() {

    var toolbar: Toolbar? = null

    val binding: T by lazy {
        DataBindingUtil.inflate(layoutInflater, layoutId, null, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initVMDependency()
        attachBinding()
    }

    fun hideHomeAsUpIcon() {
        supportActionBar?.apply {
            setHomeButtonEnabled(false)
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }
    }

    abstract fun getVM(): BaseViewModel?

    fun setUpToolbar(toolbar: Toolbar, titleId: Int) {
        this.toolbar = toolbar
        toolbar.title = getString(titleId)
        setSupportActionBar(toolbar)
    }

    open fun attachBinding() {}

    private fun initVMDependency() {
        getVM()?.bus?.observe(this, { event ->
            when (event) {
                is ToastEvent -> {
                    Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show()
                }
                is ErrorEvent -> {
                    Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show()
                }
                is SuccessEvent -> {
                    Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    handleEvent(event)
                }
            }
        })
    }

    open fun handleEvent(event: BaseEvent) {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}



