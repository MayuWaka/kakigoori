package app.wakayama.tama.kakigoori

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
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

//        googlemapからのアクティビティの開始を許可するためのもの（テスト）
        val data: Uri? = intent?.data

        // Figure out what to do based on the intent type
        if (intent?.type?.startsWith("image/") == true) {
            // Handle intents with image data ...
            val intenti: Intent? = intent
        } else if (intent?.type == "text/plain") {
            // Handle intents with text ...
            val intentt: Intent? = intent
        }


    }


}
