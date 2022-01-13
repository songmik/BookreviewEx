package com.example.a12_bookreview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a12_bookreview.adapter.BookAdapter
import com.example.a12_bookreview.api.BookService
import com.example.a12_bookreview.databinding.ActivityMainBinding
import com.example.a12_bookreview.model.BestSellerDto
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBookRecyclerView()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://book.interpark.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val bookService = retrofit.create(BookService::class.java)

        bookService.getBestSellerBooks("E25063E7B495514941EBD9CA8BF4EF291F1855B94D5205D4EE1AFBECAFF1EE34")
            .enqueue(object: Callback<BestSellerDto>{
                override fun onResponse(call: Call<BestSellerDto>, response: Response<BestSellerDto>) {
                    if(response.isSuccessful.not()){
                        return
                    }
                    response.body()?.let {
                        Log.d(TAG, it.toString())

                        it.books.forEach { book ->
                            Log.d(TAG, book.toString())
                        }
                        adapter.submitList(it.books)
                    }

                }

                override fun onFailure(call: Call<BestSellerDto>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

    }

    fun initBookRecyclerView(){

        adapter = BookAdapter()
        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bookRecyclerView.adapter = adapter
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
