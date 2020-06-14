package app.wakayama.tama.kakigoori

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ここで1秒間スリープし、スプラッシュを表示させたままにする。
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
        }
        // スプラッシュthemeを通常themeに変更する
        setTheme(R.style.AppTheme)

        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        setupWithNavController(bottom_navigation, navController)


        // GoogleMapからのアクティビティの開始(場所を共有のIntent受取り)
//        val data: Uri? = intent?.data

        val shopform = Intent(this, ShopFormActivity::class.java)

        // Figure out what to do based on the intent type
        if (intent?.type?.startsWith("image/") == true) {
            // Handle intents with image data ...
        } else if (intent?.type == "text/plain") {
            // Handle intents with text ...

            val infoText: String = intent.getStringExtra(Intent.EXTRA_TEXT)
            val infoTextArr = infoText.split("\n")

            shopform.putExtra("Mode", "0")  // 他アプリから追加
            shopform.putExtra("ID", R.drawable.uranai0)
            shopform.putExtra("shopname", infoTextArr[0])
            shopform.putExtra("shopaddress", infoTextArr[1])
            shopform.putExtra("memo", infoTextArr[2])
            shopform.putExtra("url", infoTextArr[3])

            //お店の情報の登録画面を呼び出す(追加登録)
            startActivity(shopform)
        }
    }
}
