package com.rahulverlekar.pokedex.base

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

abstract class BaseRvAdapter<out T : ViewDataBinding>(@LayoutRes val layoutId: Int) :
    RecyclerView.Adapter<BaseRvAdapter<T>.CustomViewHolder>() {

    var searchQuery: String = ""

    var highlightableViewIds: ArrayList<Int>? = null

    inner class CustomViewHolder(val binding: T) : RecyclerView.ViewHolder(binding.root) {
        fun bind(obj: Any) {
//            binding.setVariable(BR.model, obj)
            binding.executePendingBindings()
            highlightableViewIds?.let { ids ->
                val textViews = arrayListOf<TextView>()
                ids.forEach{
                    textViews.add((binding.root.findViewById(it) as TextView))
                }
                setFilterHighlight(textViews)
            }
        }

        fun setFilterHighlight(textViews: ArrayList<TextView>) {
            textViews.forEach {
                with(it.text) {
                    if (toString().contains(searchQuery, true)) {
                        val searchStartIndex = toString().toLowerCase(Locale.ENGLISH)
                            .indexOf(searchQuery.toLowerCase(Locale.ENGLISH))
                        val searchEndIndex = searchStartIndex + searchQuery.length
                        val subStringToReplace = substring(searchStartIndex, searchEndIndex)
                        it.text = Html.fromHtml(
                            this.toString().replace(
                                subStringToReplace,
                                "<font color='red'>$subStringToReplace</font>"
                            )
                        )
                    }
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<T>(layoutInflater, viewType, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) =
        holder.bind(getObjAtPosition(position))

    protected abstract fun getObjAtPosition(position: Int): Any

    override fun getItemViewType(position: Int) = getLayoutIdForPosition(position)

    protected abstract fun getLayoutIdForPosition(position: Int): Int
}