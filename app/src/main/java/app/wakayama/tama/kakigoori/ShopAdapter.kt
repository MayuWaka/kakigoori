package app.wakayama.tama.kakigoori

import android.widget.ImageView
import android.widget.LinearLayout
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_list.view.*
import java.text.SimpleDateFormat
import java.util.*


class ShopAdapter(
    private val context: Context,
    private var ShopList: OrderedRealmCollection<Shop>?,
    private var listener: OnItemClickListener,
    private val autoUpdate: Boolean
) :
    RealmRecyclerViewAdapter<Shop, ShopAdapter.TaskViewHolder>(ShopList, autoUpdate) {

    override fun getItemCount(): Int = ShopList?.size ?: 0

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val shop: Shop = ShopList?.get(position) ?: return

        holder.container.setOnClickListener{
            listener.onItemClick(shop)
        }
        holder.imageView.setImageResource(shop.imageId)
        holder.contentTextView.text = shop.content
        holder.dateTextView.text =
            SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPANESE).format(shop.createdAt)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TaskViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.activity_list, viewGroup, false)
        return TaskViewHolder(v)
    }

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container : LinearLayout = view.container
        val imageView: ImageView = view.imageView
        val contentTextView: TextView = view.contentTextView
        val dateTextView: TextView = view.dateTextView
    }

    interface OnItemClickListener {
        fun onItemClick(item: Shop)
    }

}