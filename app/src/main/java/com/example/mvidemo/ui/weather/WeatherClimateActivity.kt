package com.example.mvidemo.ui.weather

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvidemo.common.MVIApplication.Companion.instance
import com.example.mvidemo.databinding.ActivityWeatherClimateBinding
import com.example.mvidemo.repository.model.WCWeather
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class WeatherClimateActivity : AppCompatActivity() {
    private var climates = ArrayList<Int>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mApplication: Application

    lateinit var weatherClimateViewModel: WeatherClimateViewModel
    private var itemPrintAdapter: WeatherClimateAdapter? = null
    lateinit var weatherBinding: ActivityWeatherClimateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherBinding = ActivityWeatherClimateBinding.inflate(layoutInflater)
        setContentView(weatherBinding.root)
        instance?.getComponent()?.inject(this)
        weatherClimateViewModel =
            ViewModelProvider(this, viewModelFactory)[WeatherClimateViewModel::class.java]
        weatherBinding.rvItem.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        itemPrintAdapter = WeatherClimateAdapter(this, climates)
        weatherBinding.rvItem.adapter = itemPrintAdapter

        //itemPrintAdapter?.setItem(climate)
        val currentDate: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        weatherBinding.tvDate.text = currentDate
        lifecycleScope.launch {
            weatherClimateViewModel.weatherIntent.send(WeatherIntent.FetchClimate)
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            weatherClimateViewModel.weatherState.collect { it ->
                when (it) {
                    is WeatherState.Idle -> {

                    }

                    is WeatherState.WeatherDetail -> {
                        weatherBinding.pbLoading.visibility = View.GONE
                        val list = it.weather.list
                        if (list != null) {
                            for (i in list) {
                                i?.deg?.let { it1 -> climates.add(it1) }
                            }
                        }
                        val hashMap = mutableMapOf<Int, List<WCWeather?>>()
                        if (list != null) {
                            for (i in list) {
                                i?.weather?.let { it1 ->
                                    i.clouds?.let { it2 ->
                                        hashMap.put(it2, it1)
                                    }
                                }
                            }
                        }
                        weatherBinding.tvClear.text =
                            hashMap.toList()[0].second[0]?.description.toString()
                        weatherBinding.rvItem?.adapter?.notifyDataSetChanged()
                    }

                    is WeatherState.Error -> {
                        weatherBinding.pbLoading.visibility = View.GONE
                        Toast.makeText(this@WeatherClimateActivity, it.error, Toast.LENGTH_LONG)
                            .show()
                    }

                    is WeatherState.Loading -> {
                        weatherBinding.pbLoading.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}