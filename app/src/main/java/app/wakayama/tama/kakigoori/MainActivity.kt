package app.wakayama.tama.kakigoori

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import io.realm.Sort

class MainActivity : AppCompatActivity() {

    //Realm変数
    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shopform = Intent(this, ShopFormActivity::class.java)

        val taskList = readAll()

        if (taskList.isEmpty()) {
            createDummyData()
        }

//        //Google Map表示
////        val mapUrl:String = "geo:0,0?q=" + lat + "," + lng  + "(" + label + ")";
//        val mapUrl:String = "geo:0,0?q=名古屋 かき氷(お店)"
//        val sendIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl))
////        val sendIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=東京駅"))
//        startActivity(sendIntent)

        //FloatingActionButtonの動作実装
        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Fabを押しました！", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()

            shopform.putExtra("ID", "")
            shopform.putExtra("shopname", "")
            shopform.putExtra("shopaddress", "")
            shopform.putExtra("memo", "")
            shopform.putExtra("url", "")

            //お店の情報の登録画面を呼び出す
            startActivity(shopform)

//            //お店の情報の登録画面で設定された値を取り出す
//            val name = shopform.getStringExtra("shopname")
//            val add = shopform.getStringExtra("shopaddress")
//            val memo = shopform.getStringExtra("shopaddress")
//            val url = shopform.getStringExtra("url")
//
//            //データベースへ登録
//            create(R.drawable.ic_launcher_background, name, add)
        }

        val adapter =
            ShopAdapter(this, taskList, object : ShopAdapter.OnItemClickListener {
                override fun onItemClick(item: Shop) {
//                    // クリックした処理を書く
//                    Toast.makeText(applicationContext, item.shopname + "を削除しました", Toast.LENGTH_SHORT)
//                        .show()
//                    delete(item)

                    shopform.putExtra("ID", item.id)
                    shopform.putExtra("shopname", item.shopname)
                    shopform.putExtra("shopaddress", item.address)
                    shopform.putExtra("memo", "")
                    shopform.putExtra("url", "")

                    //お店の情報の登録画面を呼び出す
                    startActivity(shopform)
                }
            }, true)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }


    fun createDummyData() {
        for (i in 0..10) {
            create(R.drawable.ic_launcher_background, "お店の名前 $i", "住所　$i")
        }
    }

    fun create(imageId: Int, content: String, address: String) {
        realm.executeTransaction {
            val task = it.createObject(Shop::class.java, UUID.randomUUID().toString())
            task.imageId = imageId
            task.shopname = content
            task.address = address
        }
    }

    fun readAll(): RealmResults<Shop> {
        return realm.where(Shop::class.java).findAll().sort("shopname", Sort.ASCENDING)
    }

    fun update(id: String, content: String) {
        realm.executeTransaction {
            val task = realm.where(Shop::class.java).equalTo("id", id).findFirst()
                ?: return@executeTransaction
            task.shopname = content
        }
    }

    fun update(task: Shop, content: String) {
        realm.executeTransaction {
            task.shopname = content
        }
    }

    fun delete(id: String) {
        realm.executeTransaction {
            val task = realm.where(Shop::class.java).equalTo("id", id).findFirst()
                ?: return@executeTransaction
            task.deleteFromRealm()
        }
    }

    fun delete(task: Shop) {
        realm.executeTransaction {
            task.deleteFromRealm()
        }
    }

    fun deleteAll() {
        realm.executeTransaction {
            realm.deleteAll()
        }

    }
}
