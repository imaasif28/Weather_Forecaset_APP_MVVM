package com.aasif.weatherforecasetmvvm.ui.weather.future.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aasif.weatherforecasetmvvm.R
import com.aasif.weatherforecasetmvvm.ui.adapter.FutureListWeatherAdapter
import com.aasif.weatherforecasetmvvm.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.future_list_weather_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class FutureListWeatherFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory: FutureListWeatherViewModelFactory by instance()

    private lateinit var viewModel: FutureListWeatherViewModel
    private lateinit var adapter: FutureListWeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.future_list_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FutureListWeatherViewModel::class.java)
        setRecyclerView()
        bindUI()
    }

    private fun setRecyclerView() {
        adapter = FutureListWeatherAdapter(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

    }

    private fun bindUI() = launch {
        val forecastWeather = viewModel.forecastWeather.await()
        forecastWeather.observe(viewLifecycleOwner, Observer {
            group_loading.visibility = View.GONE
            val forecast = it.dailyForecasts
            adapter.setForecast(forecast)
        })
    }


}
