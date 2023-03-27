package com.example.pagingpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.pagingpractice.databinding.ActivityMainBinding
import com.example.pagingpractice.viewModel.QuotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
   private lateinit var binding: ActivityMainBinding
    lateinit var adapter: QuotesAdapter
    private val viewModel: QuotesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        adapter  = QuotesAdapter()
        binding.recyclerView.adapter = adapter
        Log.e("Result","dfafdfdsfds")
        setContentView(binding.root)
        viewModel.list.observe(this, Observer {
            it.let {
                Log.e("Result","observe"+it)
                adapter.submitData(lifecycle, it)
            }
        })

    }
}