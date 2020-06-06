package app.wakayama.tama.kakigoori

import android.content.Context
import android.widget.ImageView
import android.widget.LinearLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_list.view.*


class ShopAdapter(
    private val context: Context,
    private var shopList: OrderedRealmCollection<Shop>?,
    private var listener: OnItemClickListener,
    private val autoUpdate: Boolean
) :
    RealmRecyclerViewAdapter<Shop, ShopAdapter.ShopMemoViewHolder>(shopList, autoUpdate) {

    override fun getItemCount(): Int = shopList?.size ?: 0

    override fun onBindViewHolder(holder: ShopMemoViewHolder, position: Int) {
        val shop: Shop = shopList?.get(position) ?: return

        holder.container.setOnClickListener{
            listener.onItemClick(shop)
        }
        holder.imageView.setImageResource(shop.imageId)
        holder.shopNameTextView.text = shop.shopname
//        holder.dateTextView.text =
//            SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPANESE).format(shop.createdAt)
        holder.addressTextView.text = shop.address
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ShopMemoViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.activity_list, viewGroup, false)
        return ShopMemoViewHolder(v)
    }

    class ShopMemoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container : LinearLayout = view.container
        val imageView: ImageView = view.imageView
        val shopNameTextView: TextView = view.shopNameTextView
        val addressTextView: TextView = view.addressTextView
    }

    interface OnItemClickListener {
        fun onItemClick(item: Shop)
    }

}