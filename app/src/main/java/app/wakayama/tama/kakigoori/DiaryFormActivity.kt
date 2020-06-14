package app.wakayama.tama.kakigoori
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_diary_form.*
import kotlinx.android.synthetic.main.activity_shop_form.addButton
import kotlinx.android.synthetic.main.activity_shop_form.editTextShopName
import java.util.*

class DiaryFormActivity : AppCompatActivity() {

    //Realm変数
    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_form)

        editTextShopName.setText(intent.getStringExtra("shopname"))
        diaryMemoEdit.setText(intent.getStringExtra("memo"))
        imageView2.setImageURI(Uri.parse(intent.getStringExtra("imageUri")))
        ratingBar.setRating(intent.getFloatExtra("star", 0.0F))

        var nakami = 0

        if (editTextShopName.text.toString() == "") {
            nakami = 1
        } else {
            nakami = 2
        }

        imageView2.setOnClickListener {
            selectPhoto()
        }


        //登録ボタンが押された時に
        addButton.setOnClickListener {
//            intent.putExtra("shopname", editTextShopName.text.toString())
//            intent.putExtra("shopaddress", editTextPostalAddress.text.toString())
//            intent.putExtra("memo", editTextMemo.text.toString())
//            intent.putExtra("url", editTextUrl.text.toString())
//

            //データベースへ登録
            val id: String? = intent.getStringExtra("ID")
            val name: String = editTextShopName.text.toString()
            val memo: String = diaryMemoEdit.text.toString()
            val imageUri: String = imageView2.id.toString()
            val star: Float = ratingBar.rating

            if (nakami == 1) {
                create(imageUri, name, memo, star)
            } else if (nakami == 2) {
                update( id, imageUri, name, memo, star)
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

    fun create(imageUri: String, shopName: String, memo: String, star: Float) {
        realm.executeTransaction {
            val diarycard = it.createObject(Diary::class.java, UUID.randomUUID().toString())
            diarycard.imageUri = imageUri
            diarycard.shopname = shopName
            diarycard.memo = memo
            diarycard.star = star
        }
    }


    fun update(id: String?, imageUri: String, shopName: String, memo: String, star: Float) {
        realm.executeTransaction {
            val diarycard = realm.where(Diary::class.java).equalTo("id", id).findFirst()
                ?: return@executeTransaction
            diarycard.id= id
            diarycard.imageUri = imageUri
            diarycard.shopname = shopName
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