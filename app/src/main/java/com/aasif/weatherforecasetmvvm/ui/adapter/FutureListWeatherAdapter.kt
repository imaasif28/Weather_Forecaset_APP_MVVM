package com.aasif.weatherforecasetmvvm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aasif.weatherforecasetmvvm.R
import com.aasif.weatherforecasetmvvm.data.db.entity.accuWeatherResponse.DailyForecast
import com.aasif.weatherforecasetmvvm.internal.getVectorImage


class FutureListWeatherAdapter(
    context: Context
): RecyclerView.Adapter<FutureListWeatherAdapter.WeatherViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    private var weatherList = listOf<DailyForecast>()
    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val temperatureView: TextView = itemView.findViewById(R.id.textView_temperature)
        val dateView: TextView = itemView.findViewById(R.id.textView_date)
        val conditionView: TextView = itemView.findViewById(R.id.textView_condition)
        val conditionIcon: ImageView = itemView.findViewById(R.id.imageView_condition_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView = inflater.inflate(R.layout.item_future_weather, parent, false)
        return WeatherViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val current = weatherList[position]
        holder.temperatureView.text = buildString {
        append(current.temperature.maximum.value.toString())
        append("/")
        append(current.temperature.minimum.value.toString())
    }
        holder.dateView.text = current.date.split("T")[0]
        holder.conditionView.text = current.day.iconPhrase
        val iconId = current.day.icon
        holder.conditionIcon.setImageResource(getVectorImage(iconId))
    }
    fun setForecast(weatherList: List<DailyForecast>){
        this.weatherList = weatherList
        notifyDataSetChanged()
    }
}