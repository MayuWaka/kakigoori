package app.wakayama.tama.kakigoori

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_shop_form.*

class ShopFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_form)

        //登録ボタンが押された時に
        addButton.setOnClickListener {
            intent.putExtra("shopname", editTextShopName.text)
            intent.putExtra("shopaddress", editTextPostalAddress.text)
            intent.putExtra("memo", editTextMemo.text)
            intent.putExtra("url", editTextUrl.text)

//            val name = intent.getStringExtra("shopname")
//            val add = intent.getStringExtra("shopaddress")
//            val memo = intent.getStringExtra("shopaddress")
//            val url = intent.getStringExtra("url")

            // 画面を閉じる
            finish()
        }
    }
}
