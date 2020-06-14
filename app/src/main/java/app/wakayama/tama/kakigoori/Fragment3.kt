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

        val diaryList = readAll()

        if (diaryList.isEmpty()) {
            createDummyData()
        }

        val fab: View = view.findViewById(R.id.fab)
        fab.setOnClickListener { view ->

            diaryform.putExtra("Mode", "1")  // 追加
            diaryform.putExtra("ID", "")
            diaryform.putExtra("shopname", "")
            diaryform.putExtra("imageUri", "")
            diaryform.putExtra("memo", "")
            diaryform.putExtra("star", "")
            diaryform.putExtra("date", "")

            //お店の情報の登録画面を呼び出す
            startActivity(diaryform)
        }

        var adapterDiary =
            DiaryAdopter(requireContext(), diaryList, object : DiaryAdopter.OnItemClickListener {
                override fun onItemClick(item: Diary) {

                    diaryform.putExtra("Mode", "2")  // 更新
                    diaryform.putExtra("shopname", item.shopname)
                    diaryform.putExtra("imageUri", item.imageUri)
                    diaryform.putExtra("memo", item.memo)
                    diaryform.putExtra("star", item.star)
                    diaryform.putExtra("date", item.createdAt)

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

    fun createDummyData() {
        create("A.COCOTTO", "", "ぽってりしておいしかった。", 3.0F)
        create("A.COCOTTO", "", "ぼってりだった。", 1.0F)
    }

    fun create(shopName: String, imageUri: String, memo: String, star: Float) {
        realm.executeTransaction {
            val diarycard = it.createObject(Diary::class.java, UUID.randomUUID().toString())
            diarycard.shopname = shopName
            diarycard.imageUri = imageUri
            diarycard.memo = memo
            diarycard.star = star
        }
    }

    fun readAll(): RealmResults<Diary> {
          return realm.where(Diary::class.java).findAll().sort("createdAt", Sort.DESCENDING)
    }
}