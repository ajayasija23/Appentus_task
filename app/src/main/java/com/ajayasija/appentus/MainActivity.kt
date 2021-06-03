package com.ajayasija.appentus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.ajayasija.appentus.databinding.ActivityMainBinding
import com.ajayasija.appentus.viewmodel.ImageFactory
import com.ajayasija.appentus.viewmodel.ImageViewModel
import com.ajayasija.appentus.web.ApiService
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ImageAdapter
    private lateinit var viewModel:ImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set up view binding
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //pass apiservice object to viewModel
        val factory=ImageFactory(ApiService.getApiService())
        viewModel= ViewModelProvider(this,factory)[ImageViewModel::class.java]

        //setup recyclerview adapter

        adapter=ImageAdapter(this)
        binding.rvGalary.adapter=adapter

        //submit page data
        lifecycleScope.launch{
            viewModel.data.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}