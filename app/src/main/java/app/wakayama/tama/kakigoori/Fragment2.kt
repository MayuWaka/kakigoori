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
import kotlinx.android.synthetic.main.fragment_2.*
import java.util.*


class Fragment2 : Fragment() {

    //Realm変数
    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val layout = inflater.inflate(R.layout.fragment_2, container, false)

//        val shopform = Intent(requireContext(), ShopFormActivity::class.java)

        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shopform = Intent(requireContext(), ShopFormActivity::class.java)

        val taskList = readAll()

        if (taskList.isEmpty()) {
            createDummyData()
        }

        //FloatingActionButtonの動作実装
        val fab: View = view.findViewById(R.id.fab)
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

        var adapterShop =
            ShopAdapter(requireContext(), taskList, object : ShopAdapter.OnItemClickListener {
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
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapterShop
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()   //画面終了時にRealmを終了する
    }

    fun createDummyData() {
        for (i in 0..10) {
            create(R.drawable.ic_launcher_background, "お店の名前 $i", "住所 $i")
        }
    }

    fun create(imageId: Int, name: String, address: String) {
        realm.executeTransaction {
            val shop = it.createObject(Shop::class.java, UUID.randomUUID().toString())
            shop.imageId = imageId
            shop.shopname = name
            shop.address = address
        }
    }

    fun readAll(): RealmResults<Shop> {
//        return realm.where(Shop::class.java).findAll().sort("createdAt", Sort.ASCENDING)
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