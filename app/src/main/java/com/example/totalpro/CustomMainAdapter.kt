package com.example.totalpro

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.totalpro.databinding.ItemViewBinding

class CustomMainAdapter(val dataList: MutableList<DataList>) :
    RecyclerView.Adapter<CustomMainAdapter.CustomMainViewHolder>() {

    var filterList = dataList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomMainViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomMainViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: CustomMainViewHolder, position: Int) {
        val fl = filterList[position]
        val binding = holder.binding
        binding.ivPicture.setImageResource(fl.ivPicture)
        binding.tvTitle.text = fl.tvTitle
        binding.tvPrice.text = fl.tvPrice
        binding.tvText.text = fl.tvText
        binding.edtPlace.text = fl.edtPlace
        // 데이트 피커와 타임 피커에서 선택한 날짜와 시간으로 가져오는 방법은?
        binding.edtDate.text = fl.edtDate
        binding.edtTime.text = fl.edtTime
        binding.root.setOnClickListener {
            val tm = "내용: ${binding.tvText.text}, 장소: ${binding.edtPlace.text}, " +
                    "거래 희망 날짜: ${binding.edtDate.text}, 거래 희망 시간: ${binding.edtTime.text}"
            Toast.makeText(binding.root.context, tm, Toast.LENGTH_SHORT).show()
            Log.e("CustomMainAdapter", tm)
        }
    }

    class CustomMainViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    fun filter(query: String) {
        filterList.clear()
        if (query.isEmpty()) {
            filterList.addAll(dataList)
        } else {
            for (fl in dataList) {
                if (fl.tvTitle.contains(query))
                    filterList.add(fl)
            }
        }
        notifyDataSetChanged()
    }
}

