package app.wakayama.tama.kakigoori

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_shop_form.*
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

        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shopform = Intent(requireContext(), ShopFormActivity::class.java)

        val shopList = readAll()

        if (shopList.isEmpty()) {
            createDummyData()
        }

        //FloatingActionButtonの動作実装
        val fab: View = view.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Fabを押しました！", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()

            shopform.putExtra("Mode", "1")  // 追加
            shopform.putExtra("ID", "")
            shopform.putExtra("shopname", "")
            shopform.putExtra("shopaddress", "")
            shopform.putExtra("memo", "")
            shopform.putExtra("url", "")

            //お店の情報の登録画面を呼び出す(追加登録)
            startActivity(shopform)
        }

        val adapterShop =
            ShopAdapter(requireContext(), shopList, object : ShopAdapter.OnItemClickListener {
                override fun onItemClick(item: Shop) {
                    // クリックした処理を書く
                    AlertDialog.Builder(requireContext())
                        .setIcon(R.drawable.uranai0)
                        .setTitle("操作選択")
                        .setMessage("【" + item.shopname + "】\nが選択されました。\n\n編集 or 削除？")
                        .setPositiveButton(
                            "編集"
                        ) { dialog, which ->
                            shopform.putExtra("Mode", "2")  // 更新
                            shopform.putExtra("ID", item.id)
                            shopform.putExtra("shopname", item.shopname)
                            shopform.putExtra("shopaddress", item.address)
                            shopform.putExtra("memo", item.memo)
                            shopform.putExtra("url", item.url)

                            //お店の情報の登録画面を呼び出す(更新登録)
                            startActivity(shopform)

//                            Toast.makeText(requireContext(), item.shopname + "を編集しました", Toast.LENGTH_SHORT)
//                                .show()
                        }
                        .setNeutralButton(
                            "削除"
                        ) { dialog, which ->
                            AlertDialog.Builder(requireContext())
                                .setIcon(R.drawable.uranai0)
                                .setTitle("削除確認")
                                .setMessage("【" + item.shopname + "】\n\nのお店情報を削除しますか？")
                                .setPositiveButton(
                                    "OK"
                                ) { dialog, which ->
                                    //削除実行
                                    val shopname:String = item.shopname
                                    delete(item)
                                    Toast.makeText(requireContext(), shopname + "を削除しました", Toast.LENGTH_SHORT)
                                        .show()
                                }
                                .setNegativeButton("Cancel", null)
                                .show();
                        }
                        .setNegativeButton("Cancel", null)
                        .show();
                }
//            }, object : ShopAdapter.OnItemLongClickListener {
//                override fun onItemLongClick(item: Shop) {
//
//                    AlertDialog.Builder(requireContext())
//                        .setTitle(item.shopname + " のお店情報を削除しますか？")
//                        .setIcon(R.drawable.uranai0)
//                        .setPositiveButton(
//                            "Yes"
//                        ) { dialog, which -> }
//                        .setNegativeButton(
//                            "No"
//                        ) { dialog, which -> }
//                        .show()
//
//                    Toast.makeText(requireContext(), item.shopname + "を削除しました", Toast.LENGTH_SHORT)
//                        .show()
////                  delete(item)
//                    AlertDialog.Builder(requireContext())
//                        .setTitle("削除")
//                }
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
        create(R.drawable.uranai0, "A.COCOTTO", "5WFR+Q2 名古屋市、愛知県","ぽってり","https://tabelog.com/aichi/A2301/A230106/23064440/")
        create(R.drawable.uranai0, "吾妻茶寮", "5W53+WF 名古屋市、愛知県","エスプーマ","https://tabelog.com/aichi/A2301/A230105/23053848/")
        create(R.drawable.uranai0, "甘味処 柴ふく", "5W23+MW 名古屋市、愛知県","きなこ美味しい","https://tabelog.com/aichi/A2301/A230105/23031436/")
        create(R.drawable.uranai0, "かき氷専門店 あんどりゅ。", "5W64+3G 名古屋市、愛知県","氷がふわふわ","https://tabelog.com/aichi/A2301/A230105/23054827/")
        create(R.drawable.uranai0, "shizuku", "5V98+76 名古屋市、愛知県","お茶","https://tabelog.com/aichi/A2301/A230101/23060207/")
//        for (i in 0..10) {
//            create(R.drawable.ic_launcher_background, "お店の名前 $i", "住所 $i")
//        }
    }

    fun create(imageId: Int, shopname: String, address: String, memo: String, url: String) {
        realm.executeTransaction {
            val shop = it.createObject(Shop::class.java, UUID.randomUUID().toString())
            shop.imageId = imageId
            shop.shopname = shopname
            shop.address = address
            shop.memo = memo
            shop.url = url
        }
    }

    fun readAll(): RealmResults<Shop> {
//        return realm.where(Shop::class.java).findAll().sort("createdAt", Sort.ASCENDING)
        return realm.where(Shop::class.java).findAll().sort("shopname", Sort.ASCENDING)
    }

    fun update(id: String, shopname: String) {
        realm.executeTransaction {
            val shop = realm.where(Shop::class.java).equalTo("id", id).findFirst()
                ?: return@executeTransaction
            shop.shopname = shopname
        }
    }

    fun update(shop: Shop, shopname: String) {
        realm.executeTransaction {
            shop.shopname = shopname
        }
    }

    fun delete(id: String) {
        realm.executeTransaction {
            val shop = realm.where(Shop::class.java).equalTo("id", id).findFirst()
                ?: return@executeTransaction
            shop.deleteFromRealm()
        }
    }

    fun delete(shop: Shop) {
        realm.executeTransaction {
            shop.deleteFromRealm()
        }
    }

    fun deleteAll() {
        realm.executeTransaction {
            realm.deleteAll()
        }
    }
}