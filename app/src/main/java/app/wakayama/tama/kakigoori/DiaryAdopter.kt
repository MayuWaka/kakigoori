package app.wakayama.tama.kakigoori

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_diary.view.*


class DiaryAdopter(
    private val context: Context,
    private var diaryList: OrderedRealmCollection<Diary>?,
    private var listener: OnItemClickListener,
    private val autoUpdate: Boolean
) :
    RealmRecyclerViewAdapter<Diary, DiaryAdopter.DiaryMemoViewHolder>(diaryList, autoUpdate) {

    override fun getItemCount(): Int = diaryList?.size ?: 0

    override fun onBindViewHolder(holder: DiaryMemoViewHolder, position: Int) {
        val diary: Diary = diaryList?.get(position) ?: return

        holder.container.setOnClickListener{
            listener.onItemClick(diary)
        }

        holder.shopNameTextView.text = diary.shopname
        holder.imageUri.setImageURI(Uri.parse(diary.imageUri))
        holder.memo.text= diary.memo
        holder.star.setRating(diary.star)
        holder.createdAt.text = diary.createdAt.toString().format("yyyy/MM/dd HH:mm:ss")
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): DiaryMemoViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.activity_diary, viewGroup, false)
        return DiaryMemoViewHolder(v)
    }

    class DiaryMemoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container : LinearLayout = view.container
        val shopNameTextView: TextView = view.shopNameTextView
        val imageUri: ImageView = view.imageView
        val star: RatingBar = view.ratingBar2
        val memo: TextView = view.editTextMemo
        val createdAt: TextView = view.editTextDate
    }

    interface OnItemClickListener {
        fun onItemClick(item: Diary)
    }
}