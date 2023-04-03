package com.example.totalpro

import android.app.DatePickerDialog
import android.app.ProgressDialog.show
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.totalpro.databinding.FragmentMainBinding
import com.example.totalpro.databinding.SellBinding

class MainFragment : Fragment(), View.OnClickListener, DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    lateinit var binding: FragmentMainBinding
    lateinit var dataList: MutableList<DataList>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        binding.btnSell.setOnClickListener(this)

        dataList = mutableListOf<DataList>()

        for (i in 1..8) {
            if (i % 2 == 0) {
                dataList.add(
                    DataList(
                        R.drawable.dfdf,
                        "맥북 M1Pro 14인치${i}",
                        "10${i}원",
                        "",
                        "",
                        "",
                        ""
                    )
                )
            } else {
                dataList.add(
                    DataList(
                        R.drawable.dfdf,
                        "맥북 M1Pro 14인치${i}",
                        "10${i}원",
                        "",
                        "",
                        "",
                        ""
                    )
                )
            }
        }

        binding.recyclerView.adapter = CustomMainAdapter(dataList)
        val layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.setHasFixedSize(true)

        return binding.root

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSell -> {
                var sellBinding: SellBinding
                val dialogBuilder = AlertDialog.Builder(requireContext())
                var userDialog: AlertDialog
                sellBinding = SellBinding.inflate(layoutInflater)
                dialogBuilder.setTitle("내 물건 팔기")
                dialogBuilder.setView(sellBinding.root)
                userDialog = dialogBuilder.create()
                userDialog.show()
                sellBinding.btnSeletPlace.setOnClickListener {
                    val items = arrayOf<String>("서울", "인천", "청주", "김해")
                    AlertDialog.Builder(requireContext()).run {
                        setTitle("거래 희망 장소를 선택하세요.")
                        setSingleChoiceItems(items, 0, object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                sellBinding.btnSeletPlace.text = items[which]
                            }
                        })
                        setPositiveButton("확인", null)
                        show()
                    }
                }
                sellBinding.btnSeletDate.setOnClickListener {
                    DatePickerDialog(requireContext(), this, 2023, 5 - 1, 23).show()
                }
                sellBinding.btnSeletTime.setOnClickListener {
                    TimePickerDialog(requireContext(), this, 23, 59, true).show()
                }
                sellBinding.btnCancel.setOnClickListener {
                    userDialog.dismiss()
                }
                sellBinding.btnSelect.setOnClickListener {
                    val newData = DataList(
                        R.drawable.user,
                        sellBinding.edtTitle.text.toString(),
                        sellBinding.edtPrice.text.toString(),
                        sellBinding.edtText.text.toString(),
                        sellBinding.btnSeletPlace.text.toString(),
                        sellBinding.btnSeletDate.text.toString(),
                        sellBinding.btnSeletTime.text.toString()
                    )
                    binding.recyclerView.adapter?.notifyDataSetChanged()
                    dataList.add(newData)
                    Toast.makeText(requireContext(), "판매글 등록이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                    userDialog.dismiss()
                }
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {}
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {}
}


