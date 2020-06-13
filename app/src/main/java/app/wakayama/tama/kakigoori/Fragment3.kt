package app.wakayama.tama.kakigoori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_diary.*
import kotlinx.android.synthetic.main.activity_diary_form.*
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

        addButton.setOnClickListener {
            selectPhoto()
        }


        val fab: View = view.findViewById(R.id.fab)
        fab.setOnClickListener { view ->

            diaryform.putExtra("imageUri", "")
            diaryform.putExtra("shopname", "")
            diaryform.putExtra("memo", "")
            diaryform.putExtra("star", "")

            //お店の情報の登録画面を呼び出す
            startActivity(diaryform)

            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != AppCompatActivity.RESULT_OK) {
            return
        }
        when (requestCode) {
            READ_REQUEST_CODE -> {
                try {
                    data?.data?.also { uri ->
                        val inputStream = requireContext().contentResolver.openInputStream(uri)
                        val image = BitmapFactory.decodeStream(inputStream)
                        val imageView = imageView
                        imageView.setImageBitmap(image)
                    }
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "エラーが発生しました", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun selectPhoto() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }
        startActivityForResult(intent, READ_REQUEST_CODE)
    }

    companion object {
        private const val READ_REQUEST_CODE: Int = 42
    }


    override fun onDestroy() {
    super.onDestroy()
    realm.close()   //画面終了時にRealmを終了する
    }

    fun create(imageUri: String, name: String, address: String, memo: String, star: String) {
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