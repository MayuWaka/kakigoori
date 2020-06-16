package app.wakayama.tama.kakigoori

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_diary_form.*
import java.util.*

class DiaryFormActivity : AppCompatActivity() {

    //Realm変数
    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    private var imageUri: Uri = Uri.parse("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_form)

        val intentMode: Int = intent.getStringExtra("Mode").toInt()

        editTextShopName.setText(intent.getStringExtra("shopname"))
        imageUri = Uri.parse(intent.getStringExtra("imageUri"))
        imageView2.setImageURI(imageUri)
        diaryMemoEdit.setText(intent.getStringExtra("memo"))
        ratingBar.setRating(intent.getFloatExtra("star", 0.0F))
//        editTextDate.setText(intent.getStringExtra("date"))

        // 写真選択
        imageView2.setOnClickListener {
            selectPhoto()
        }

        // 登録ボタンが押された時に
        addButton.setOnClickListener {
            //データベースへ登録
            val id: String? = intent.getStringExtra("ID")
            val shopName: String = editTextShopName.text.toString()
            val imageUri: String = imageUri.toString()
            val memo: String = diaryMemoEdit.text.toString()
            val star: Float = ratingBar.rating.toFloat()

            if (intentMode == 1) {
                create(shopName, imageUri, memo, star)
            } else if (intentMode == 2) {
                update(id, shopName, imageUri, memo, star)
            }

            // 画面を閉じる
            finish()
        }

//        //Google Map表示
//        mapButton.setOnClickListener {
////            val mapUrl:String = "geo:0,0?q=" + lat + "," + lng  + "(" + label + ")";
////            val mapUrl:String = "geo:0,0?q=名古屋 かき氷(お店)"
////            val mapUrl:String = "http://plus.codes/" + "5WFR+Q2 名古屋市、愛知県"
//            val mapUrl:String = "http://plus.codes/" + editTextPostalAddress.text +"?q=20"
//            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl))
//            mapIntent.setPackage("com.google.android.apps.maps");
//            startActivity(mapIntent)
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()   //画面終了時にRealmを終了する
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

    fun update(id: String?, shopName: String, imageUri: String, memo: String, star: Float) {
        realm.executeTransaction {
            val diarycard = realm.where(Diary::class.java).equalTo("id", id).findFirst()
                ?: return@executeTransaction
            diarycard.shopname = shopName
            diarycard.imageUri = imageUri
            diarycard.memo = memo
            diarycard.star = star
        }
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
                        imageUri = uri
                        val inputStream = contentResolver.openInputStream(uri)
                        val image = BitmapFactory.decodeStream(inputStream)
                        val imageView = imageView2
                        imageView.setImageBitmap(image)
                    }
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, "エラーが発生しました", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    companion object {
        private const val READ_REQUEST_CODE: Int = 42
    }
    private fun selectPhoto() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }
        startActivityForResult(intent, DiaryFormActivity.READ_REQUEST_CODE)
    }
}