package app.wakayama.tama.kakigoori

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
