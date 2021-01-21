package com.jetosend.baselibrary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter<T, DB : ViewDataBinding>(
    @LayoutRes val layoutId: Int,
    private var datas: MutableList<T> = mutableListOf(),
    private val onViewCreate: ((bind: DB, data: T, position: Int) -> Unit)? = null
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    lateinit var bind: ViewDataBinding

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        onViewCreate?.invoke(holder.bind as DB, datas[position], position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val bind = DataBindingUtil.inflate<DB>(
            LayoutInflater.from(parent.context), layoutId, parent, false
        )
        return BaseViewHolder(bind)
    }

    override fun getItemCount(): Int = datas.size

    class BaseViewHolder(bind: ViewDataBinding) : RecyclerView.ViewHolder(bind.root) {
        val bind = bind
    }

    fun getItem(position: Int) = datas[position]

    fun getAll() = datas

    fun add(data: T) {
        datas.add(data)
        notifyItemInserted(datas.size)
    }

    fun add(newDatas: MutableList<T>) {
        datas.addAll(newDatas)
        notifyItemInserted(datas.size)
    }

    fun addNew(newDatas: MutableList<T>) {
        notifyItemRangeRemoved(0, datas.size)
        clear()
        datas.addAll(newDatas)
        notifyItemInserted(datas.size)
    }

    fun set(index: Int, data: T) {
        datas[index] = data
    }

    fun remove(position: Int) {
        notifyItemRemoved(position)
        datas.remove(getItem(position))
    }

    fun remove(t: T) {
        datas.remove(t)
    }

    fun clear() {
        val size = itemCount
        notifyItemRangeRemoved(0, size)
        datas.clear()
    }

}