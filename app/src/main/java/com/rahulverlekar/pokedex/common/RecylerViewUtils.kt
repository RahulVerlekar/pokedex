package com.rahulverlekar.pokedex.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahulverlekar.pokedex.BR


class DataBindingVH(view: View) : RecyclerView.ViewHolder(view) {
    lateinit var binding: ViewDataBinding

    companion object {
        fun <E> createVH(parent: ViewGroup, @LayoutRes layoutId: Int): DataBindingVH {
            val inflater = LayoutInflater.from(parent.context)
            val binding =
                DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutId, parent, false)
            val viewHolder = DataBindingVH(binding.root)
            viewHolder.binding = binding
            return viewHolder
        }
    }
}

interface RecyclerViewCallback {
    // This will be the base interface for any callback that will be passed to the view holder
    fun onClick(item: ListItem) {
    }
}

interface ListItem {
    fun getType(): Int {
        return 0
    }
}

class LinearLayoutAdapter<E : ListItem>(private val layoutMap: Map<Int, Int>) :
    RecyclerView.Adapter<DataBindingVH>(),
    RecyclerViewCallback {
    var dataSource: List<E> = arrayListOf()
    var callback: RecyclerViewCallback? = null
    var width: Int? = null
    var height: Int? = null

    constructor(layoutId: Int) : this(mutableMapOf<Int, Int>(Pair(0, layoutId)))

    override fun onBindViewHolder(holder: DataBindingVH, position: Int) {
        if (callback != null) {
            holder.binding.setVariable(BR.callback, callback)
        }
        holder.binding.setVariable(BR.item, dataSource[position])
        holder.binding.executePendingBindings()
    }

    override fun getItemViewType(position: Int): Int {
        return dataSource[position].getType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingVH {
        val vh = DataBindingVH.createVH<E>(parent, layoutMap[viewType]!!)
        width?.let { vh.itemView.layoutParams.width = it }
        height?.let { vh.itemView.layoutParams.height = it }
        return vh
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
}

// These is an extension functions that will allow us to bind datasource to the recylerview
fun <E : ListItem> RecyclerView.addDataSource(
    dataSource: List<E>,
    @LayoutRes layoutId: Int,
    callback: RecyclerViewCallback?,
    @RecyclerView.Orientation orientation: Int = RecyclerView.VERTICAL,
    layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context, orientation, false)
): LinearLayoutAdapter<E> {
    val adapter = LinearLayoutAdapter<E>(layoutId)
    adapter.callback = callback
    adapter.dataSource = dataSource
    this.layoutManager = layoutManager
    adapter.notifyDataSetChanged()
    setAdapter(adapter)
    return adapter
}

fun <E : ListItem> RecyclerView.addDataSource(
    dataSource: List<E>,
    layoutMap: Map<Int, Int>,
    callback: RecyclerViewCallback?,
    @RecyclerView.Orientation orientation: Int = RecyclerView.VERTICAL,
    layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context, orientation, false)
) {
    val adapter = LinearLayoutAdapter<E>(layoutMap)
    adapter.callback = callback
    adapter.dataSource = dataSource
    this.layoutManager = layoutManager
    adapter.notifyDataSetChanged()
    setAdapter(adapter)
}

fun <E : ListItem> RecyclerView.addDataSource(
    dataSource: List<E>,
    @LayoutRes layoutId: Int,
    callback: RecyclerViewCallback?,
    @RecyclerView.Orientation orientation: Int = RecyclerView.VERTICAL
): LinearLayoutAdapter<E> {
    val adapter = LinearLayoutAdapter<E>(layoutId)
    adapter.callback = callback
    adapter.dataSource = dataSource
    layoutManager = LinearLayoutManager(context, orientation, false)
    adapter.notifyDataSetChanged()
    setAdapter(adapter)
    return adapter
}

fun <E : ListItem> RecyclerView.addDataSource(
    dataSource: List<E>,
    layoutMap: Map<Int, Int>,
    callback: RecyclerViewCallback?,
    @RecyclerView.Orientation orientation: Int = RecyclerView.VERTICAL
) {
    val adapter = LinearLayoutAdapter<E>(layoutMap)
    adapter.callback = callback
    adapter.dataSource = dataSource
    layoutManager = LinearLayoutManager(context, orientation, false)
    adapter.notifyDataSetChanged()
    setAdapter(adapter)
}

fun <E : ListItem> RecyclerView.addDataSource(
    dataSource: List<E>,
    @LayoutRes layoutId: Int,
    callback: RecyclerViewCallback?,
    @RecyclerView.Orientation orientation: Int = RecyclerView.VERTICAL,
    itemDecoration: RecyclerView.ItemDecoration
) {
    val adapter = LinearLayoutAdapter<E>(layoutId)
    adapter.callback = callback
    adapter.dataSource = dataSource
    layoutManager = LinearLayoutManager(context, orientation, false)
    removeItemDecoration(itemDecoration)
    addItemDecoration(itemDecoration)
    adapter.notifyDataSetChanged()
    setAdapter(adapter)
}

fun <E : ListItem> RecyclerView.addGridDataSource(
    dataSource: List<E>,
    layoutMap: Map<Int, Int>,
    callback: RecyclerViewCallback?,
    spanCount: Int
) {
    val adapter = LinearLayoutAdapter<E>(layoutMap)
    adapter.callback = callback
    adapter.dataSource = dataSource
    layoutManager = GridLayoutManager(context, spanCount)
    adapter.notifyDataSetChanged()
    setAdapter(adapter)
}

fun <E : ListItem> RecyclerView.addGridDataSource(
    dataSource: List<E>,
    @LayoutRes layoutId: Int,
    callback: RecyclerViewCallback?,
    spanCount: Int
) {
    addGridDataSource(dataSource, mutableMapOf(Pair(0, layoutId)), callback, spanCount)
}

fun <E : ListItem> RecyclerView.changeDataSet(source: List<E>) {
    @Suppress("UNCHECKED_CAST")
    (adapter as? LinearLayoutAdapter<E>)?.dataSource = source
    adapter?.notifyDataSetChanged()
}

fun RecyclerView.loadMore(block: () -> Unit) {
    clearOnScrollListeners()
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (!recyclerView.canScrollVertically(1)) {
                block()
            }
        }
    })
}