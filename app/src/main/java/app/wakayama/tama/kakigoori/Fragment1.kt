package app.wakayama.tama.kakigoori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class Fragment1 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return  inflater.inflate(R.layout.fragment_1, container, false)

        //        //Google Map表示
////        val mapUrl:String = "geo:0,0?q=" + lat + "," + lng  + "(" + label + ")";
//        val mapUrl:String = "geo:0,0?q=名古屋 かき氷(お店)"
//        val sendIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl))
////        val sendIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=東京駅"))
//        startActivity(sendIntent)

    }
}