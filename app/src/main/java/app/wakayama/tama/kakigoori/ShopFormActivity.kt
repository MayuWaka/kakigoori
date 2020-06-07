package app.wakayama.tama.kakigoori

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_shop_form.*
import java.util.*

class ShopFormActivity : AppCompatActivity() {

    //Realm変数
    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_form)

        editTextShopName.setText(intent.getStringExtra("shopname"))
        editTextPostalAddress.setText(intent.getStringExtra("shopaddress"))
        editTextMemo.setText(intent.getStringExtra("memo"))
        editTextUrl.setText(intent.getStringExtra("url"))

        //登録ボタンが押された時に
        addButton.setOnClickListener {
//            intent.putExtra("shopname", editTextShopName.text.toString())
//            intent.putExtra("shopaddress", editTextPostalAddress.text.toString())
//            intent.putExtra("memo", editTextMemo.text.toString())
//            intent.putExtra("url", editTextUrl.text.toString())
//

            val name: String  = editTextShopName.text.toString()
            val add: String  = editTextPostalAddress.text.toString()

            //データベースへ登録
//            create(R.drawable.ic_launcher_background, name, add)
            update(intent.getStringExtra("ID"), R.drawable.uranai0, name, add)

            // 画面を閉じる
            finish()
        }

        //Google Map表示
        mapButton.setOnClickListener {
//            val mapUrl:String = "geo:0,0?q=" + lat + "," + lng  + "(" + label + ")";
//            val mapUrl:String = "geo:0,0?q=名古屋 かき氷(お店)"
//            val mapUrl:String = "http://plus.codes/" + "5WFR+Q2 名古屋市、愛知県"
            val mapUrl:String = "http://plus.codes/" + editTextPostalAddress.text +"?q=20"
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl))
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()   //画面終了時にRealmを終了する
    }

    fun create(imageId: Int, content: String, address: String) {
        realm.executeTransaction {
            val shopcard = it.createObject(Shop::class.java, UUID.randomUUID().toString())
            shopcard.imageId = imageId
            shopcard.shopname = content
            shopcard.address = address
        }
    }

    fun update(id: String?, imageId: Int, content: String, address: String) {
        realm.executeTransaction {
            val shopcard = realm.where(Shop::class.java).equalTo("id", id).findFirst()
                ?: return@executeTransaction
            shopcard.imageId = imageId
            shopcard.shopname = content
            shopcard.address = address
        }
    }
}
