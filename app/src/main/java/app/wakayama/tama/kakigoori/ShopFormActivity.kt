package app.wakayama.tama.kakigoori

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_list.*
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

        var nakami = 0

        if (editTextShopName.text.toString() == "" ){
             nakami = 1
        }else {
             nakami = 2
        }


        //登録ボタンが押された時に
        addButton.setOnClickListener {
//            intent.putExtra("shopname", editTextShopName.text.toString())
//            intent.putExtra("shopaddress", editTextPostalAddress.text.toString())
//            intent.putExtra("memo", editTextMemo.text.toString())
//            intent.putExtra("url", editTextUrl.text.toString())
//

            //データベースへ登録
            val id:String? = intent.getStringExtra("ID")
            val name: String  = editTextShopName.text.toString()
            val add: String  = editTextPostalAddress.text.toString()
            val memo: String = editTextMemo.text.toString()
            val url: String = editTextUrl.text.toString()
            val imageId:Int =  R.drawable.uranai0

            if (nakami == 1){
                create(imageId ,name,add,memo,url )
            } else if (nakami == 2){
                update(id,imageId ,name,add,memo,url)
            }

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

    fun create(imageId: Int, shopName: String, address: String, memo:String, url : String) {
        realm.executeTransaction {
            val shopcard = it.createObject(Shop::class.java, UUID.randomUUID().toString())
            shopcard.imageId = imageId
            shopcard.shopname = shopName
            shopcard.address = address
            shopcard.memo = memo
            shopcard.url = url
        }
    }

    fun update(id: String?, imageId: Int, shopName: String, address: String,memo:String, url : String) {
        realm.executeTransaction {
            val shopcard = realm.where(Shop::class.java).equalTo("id", id).findFirst()
                ?: return@executeTransaction
            shopcard.imageId = imageId
            shopcard.shopname = shopName
            shopcard.address = address
            shopcard.memo = memo
            shopcard.url = url
        }
    }
}
