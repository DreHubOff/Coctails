package com.studying.cocktails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.studying.cocktails.db.model.CocktailEntity
import com.studying.cocktails.navigate.OnItemClickListener
import io.reactivex.Single
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var single: Single<List<CocktailEntity>>

    private lateinit var adapter: ListAdapter
    private lateinit var onItemClickListener: OnItemClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemClickListener) {
            onItemClickListener = context
        }
        adapter = ListAdapter(onItemClickListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list_view.layoutManager = LinearLayoutManager(view.context)
        list_view.adapter = adapter
    }

    fun update(list: List<CocktailEntity>) {
        if (this.isResumed)
            adapter.update(list)
    }
}
