package com.example.a7minutesworkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.item_select_session.view.*

class SelectSessionAdapter(val context: Context,val sessionList:ArrayList<Session>):PagerAdapter() {
    override fun getCount(): Int {
        return sessionList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
      return view==`object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var layoutInflater=LayoutInflater.from(context)
        var view=layoutInflater.inflate(R.layout.item_select_session,container,false)

var itemName=view.tv_selectedSession
        itemName.text=sessionList[position].sessionName

        container.addView(view)

return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}