package com.example.totalpro

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.totalpro.databinding.ItemViewBinding

class CustomMainAdapter(val dataList: MutableList<DataList>) :
    RecyclerView.Adapter<CustomMainAdapter.CustomMainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomMainViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomMainViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: CustomMainViewHolder, position: Int) {
        val binding = holder.binding
        binding.ivPicture.setImageResource(dataList.get(position).ivPicture)
        binding.tvTitle.text = dataList.get(position).tvTitle.toString()
        binding.tvPrice.text = dataList.get(position).tvPrice.toString()
        binding.tvText.text = dataList.get(position).tvText.toString()
        binding.edtPlace.text = dataList.get(position).edtPlace.toString()
        // 데이트 피커와 타임 피커에서 선택한 날짜와 시간으로 가져오는 방법은?
        binding.edtDate.text = dataList.get(position).edtDate.toString()
        binding.edtTime.text = dataList.get(position).edtTime.toString()
        binding.root.setOnClickListener {
            val tm = "내용: ${binding.tvText.text}, 장소: ${binding.edtPlace.text}, " +
                    "거래 희망 날짜: ${binding.edtDate.text}, 거래 희망 시간: ${binding.edtTime.text}"
            Toast.makeText(binding.root.context, tm, Toast.LENGTH_SHORT).show()
            Log.e("CustomMainAdapter", tm)
        }
    }

    class CustomMainViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)
}


