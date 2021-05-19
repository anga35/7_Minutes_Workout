package com.example.a7minutesworkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.a7minutesworkout.ExerciseModel
import com.example.a7minutesworkout.R
import kotlinx.android.synthetic.main.item_create_session.view.*

class CreateCardSessionAdapter(val context: Context, val  exerciseModelList: ArrayList<ExerciseModel>):PagerAdapter() {


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
    return view == `object`
    }

    override fun getCount(): Int {
return exerciseModelList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        var layoutInflater=LayoutInflater.from(context)
        var view=layoutInflater.inflate(R.layout.item_create_session,container,false)

var itemImage=view.img_item
        var itemTxt=view.txt_createSession
var itemCard=view.ll_cardCreateSession

        itemImage.setImageResource(exerciseModelList[position].getImage())
        itemTxt.text=exerciseModelList[position].getName()


        itemCard.setOnClickListener {


if(Constants.tempExerciseList.lastIndex<=12) {
    Constants.tempExerciseList.add(exerciseModelList[position])
    if (context is CreateExerciseSession) {
        context.refreshSelectedList(Constants.tempExerciseList)
    }
}
            else{
                Toast.makeText(context,"Maximum amount of Exercises selected",Toast.LENGTH_SHORT).show()
            }


        }

        container.addView(view)



return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
       container.removeView(`object` as View)
    }

}


