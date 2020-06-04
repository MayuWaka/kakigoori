package app.wakayama.tama.kakigoori

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

//
// Applicationクラス
// ※アプリを起動させるときに一番最初に通るクラス
//
class RealmKakigooriApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        //Realmの初期化処理
        Realm.init(this)

        val realmConfig = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfig)

    }
}