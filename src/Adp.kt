package com.example.day2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adp(private val l: List<M>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class U(v: View) : RecyclerView.ViewHolder(v) {
        val t: TextView = v.findViewById(R.id.tv)
    }

    class A(v: View) : RecyclerView.ViewHolder(v) {
        val t: TextView = v.findViewById(R.id.tv)
    }

    override fun getItemViewType(p: Int): Int {
        return if (l[p].isU) 1 else 0
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return if (p1 == 1) {
            val v = LayoutInflater.from(p0.context).inflate(R.layout.item_u, p0, false)
            U(v)
        } else {
            val v = LayoutInflater.from(p0.context).inflate(R.layout.item_a, p0, false)
            A(v)
        }
    }

    override fun getItemCount(): Int = l.size

    override fun onBindViewHolder(h: RecyclerView.ViewHolder, p: Int) {
        val m = l[p]
        if (h is U) {
            h.t.text = m.t
        } else if (h is A) {
            h.t.text = m.t
        }
    }
}