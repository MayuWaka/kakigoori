package app.wakayama.tama.kakigoori

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.fragment_3.*
import java.util.*


class Fragment3 : Fragment() {

    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val layout = inflater.inflate(R.layout.fragment_3, container, false)

        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val diaryform = Intent(requireContext(), DiaryFormActivity::class.java)
        val diary = readAll()

        val fab: View = view.findViewById(R.id.fab)
        fab.setOnClickListener { view ->

            diaryform.putExtra("ID", "")
            diaryform.putExtra("imageUri", "")
            diaryform.putExtra("shopname", "")
            diaryform.putExtra("memo", "")
            diaryform.putExtra("star", "")

            //お店の情報の登録画面を呼び出す
            startActivity(diaryform)
        }

        var adapterDiary =
            DiaryAdopter(requireContext(), diary, object : DiaryAdopter.OnItemClickListener {
                override fun onItemClick(item: Diary) {

                    diaryform.putExtra("imageUri", item.imageUri)
                    diaryform.putExtra("shopname", item.shopname)
                    diaryform.putExtra("memo", item.memo)
                    diaryform.putExtra("star", item.star)

                    //お店の情報の登録画面を呼び出す
                    startActivity(diaryform)
                }
            }, true)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapterDiary
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()   //画面終了時にRealmを終了する
    }

    fun create(imageUri: String, name: String, address: String, memo: String, star: Float) {
        realm.executeTransaction {
            val diary = it.createObject(Diary::class.java, UUID.randomUUID().toString())
            diary.imageUri = imageUri
            diary.shopname = name
            diary.memo = memo
            diary.star = star
        }
    }

    fun readAll(): RealmResults<Diary> {
    //        return realm.where(Shop::class.java).findAll().sort("createdAt", Sort.ASCENDING)
        return realm.where(Diary::class.java).findAll().sort("shopname", Sort.ASCENDING)
    }
}