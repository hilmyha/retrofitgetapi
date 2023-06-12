package com.example.retrofitgetapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Intents.Insert
import android.util.Log
import android.view.View
import com.example.retrofitgetapi.API.ApiClient
import com.example.retrofitgetapi.API.ApiResponse
import com.example.retrofitgetapi.API.Mahasiswa
import com.example.retrofitgetapi.Adapter.RvAdapter
import com.example.retrofitgetapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RvAdapter(this@MainActivity, arrayListOf())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)


    }
    override fun onResume() {
        super.onResume()
        remoteGetDataMahasiswa()
    }

    private fun remoteGetDataMahasiswa() {
        ApiClient.apiService.getMahasiswa().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val data = apiResponse?.data
                    if (data != null) {
                        setDataToAdapter(data)
                    }
                }
            }
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.d("Error", t.stackTraceToString())
            }
        })
    }
    private fun setDataToAdapter(data: List<Mahasiswa>) {
        adapter.setData(data)
    }
    fun Insert(view: View) {
        val intent = Intent(this, com.example.retrofitgetapi.Fitur.Insert::class.java)
        startActivity(intent)
    }
    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}