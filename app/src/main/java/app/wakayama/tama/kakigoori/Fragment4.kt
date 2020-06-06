package app.wakayama.tama.kakigoori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlin.random.Random

class Fragment4 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val layout = inflater.inflate(R.layout.fragment_4, container, false)


//
//      var num = Random.nextInt(8)
//
//
//        when (num) {
//            0 -> syuruiText.text = "フランボワーズ氷"
//                kekkaText.text = "甘酸っぱい恋の予感！？"
//                    background="#F08080"
//        kekkaImage=
//            1 -> syuruiText.text = "サンシャインマスカット氷"
//                kekkaText.text = "今日は平和な一日になりそう！"
//                        background="#62D0AA"
//            2 -> syuruiText.text = "チョコミント氷"
//            kekkaText.text = "お仕事、勉強がはかどる一日になりそう！"
//                    background="#00CED1"
//            3 -> syuruiText.text = "ブルーベリー氷"
//            kekkaText.text = "疲れがたまってきているかも。リフレッシュしよう！"
//                    background="#C692F5"
//            4 -> syuruiText.text = "酒粕氷"
//            kekkaText.text = "気分転換をすると良いことあるかも！！"
//                    background="#FFFFFF"
//            5 -> syuruiText.text = "マロン氷"
//            kekkaText.text = "友達とより仲良くなることが起こるかも！？"
//                    background="#FAF0E9"
//            6 -> syuruiText.text = "オレンジ氷"
//            kekkaText.text = "今日は新しい友達が出来るかも！？"
//                    background="#FFBD4C"
//            7 -> syuruiText.text = "チョコ氷"
//            kekkaText.text = "今日は全てがうまくいく予感！"
//                    background="#4F2E24"
//            8 -> syuruiText.text = "ゴールデンピーチ氷"
//            kekkaText.text = "今日は金運アップ！！"
//                    background="#FCEA6B"
//
//        }

        return layout
    }
}