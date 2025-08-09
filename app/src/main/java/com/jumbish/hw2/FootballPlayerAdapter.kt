package com.jumbish.hw2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FootballPlayerAdapter(
    var footballerList: ArrayList<FootBallers>, private val onLongItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<FootballPlayerAdapter.ViewHolder>() {
    // originalList  = complete main list, filter ke liye source
    private var originalList: ArrayList<FootBallers> = ArrayList()


    init {
        //copy kar ke rakh lete hain taki modify hone per original safe rahe
        originalList.addAll(footballerList)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // viewholer main view de rahe hain
        val rpName: TextView = itemView.findViewById(R.id.rpName)
        val rpAge: TextView = itemView.findViewById(R.id.rpAge)
        val rpImageView: ImageView = itemView.findViewById(R.id.rpImageView)


    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        // layout set kar rhe hain
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_footballers, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        // ye list ki jo size hain use count karega
        return footballerList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // ye jo list hai use view pe jodega (bind karega)
        val footballer = footballerList[position]
        holder.rpName.text = footballer.name
        holder.rpAge.text = footballer.age.toString()
        Glide.with(holder.itemView.context).load(footballer.playerImage)
            .placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_background)
            .into(holder.rpImageView)

        holder.itemView.setOnLongClickListener {
            // ye
            onLongItemClick(position)
            true
        }


        val achievement = footballer.achievement
        holder.itemView.setOnClickListener {
            Utils.showCustomToast(it.context, achievement, R.drawable.ic_check)

            // Old way
            /* val intent = Intent(it.context,FootBallerDetailActivity::class.java)
             intent.putExtra("name",footballer.name)
             intent.putExtra("age",footballer.age.toString())
             intent.putExtra("detail",footballer.achievement)
             intent.putExtra("player_image",footballer.playerImage)
             it.context.startActivity(intent)*/

            // New way @Parcelize se
            val intent = Intent(it.context, FootBallerDetailActivity::class.java)
            intent.putExtra("footballer", footballer)
            it.context.startActivity(intent)
        }
    }


    /*
    *
    *    filter function search le liye
     *   query khali ho to originalList dekhao verna filter kar ke dekhao
  */
    fun filterList(query: String) {
        footballerList = if (query.isEmpty()) {
           ArrayList( originalList)
        } else {
            ArrayList(originalList.filter {
                it.name.contains(query, ignoreCase = true)

        })
        }
        notifyDataSetChanged()
    }


    /* adapter main jo position di hai (display list) wahan se item hatana
        Return pair(removedItem,originalList) jisse ki activity undo bhi ho sake
     */

    fun removeAt(position: Int): Pair<FootBallers, Int> {
        val removedItem = footballerList.removeAt(position)


        //notify adapter about removed position in displayed List
        notifyItemRemoved(position)

        val originalIndex = originalList.indexOf(removedItem)

        if (originalIndex != -1) {
            originalList.removeAt(originalIndex)
        }

//notify that range changed to keep index safe
        notifyItemRangeChanged(position, footballerList.size)


        return Pair(removedItem, originalIndex)

    }

    /*    Restore item back (undo).we need originalIndex (where in original list it was)
         * and position (where to insert in current display list)

        * */

    fun restoreItem(item: FootBallers, originalIndex: Int, position: Int) {
        // restore originalList at correct position if possible
        if (originalIndex >= 0 && originalIndex <= originalList.size) {
            originalList.add(originalIndex, item)
        } else {
            // index pata na ho to last main jud jaega
            // fallback : add at end
            originalList.add(item)
        }


        // restore displayed at requested position

        val insertPos =
            if (position >= 0 && position <= footballerList.size) position else footballerList.size

        footballerList.add(insertPos, item)

        notifyItemInserted(insertPos)

    }

}