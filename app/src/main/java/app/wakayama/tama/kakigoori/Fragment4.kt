package app.wakayama.tama.kakigoori

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_4.*
import kotlin.random.Random

class Fragment4 : Fragment() {

    var sec :Float = 3.5f
    val imgArr = arrayOf(R.drawable.uranai1,
                         R.drawable.uranai2,
                         R.drawable.uranai3,
                         R.drawable.uranai4,
                         R.drawable.uranai5,
                         R.drawable.uranai6,
                         R.drawable.uranai7,
                         R.drawable.uranai8,
                         R.drawable.uranai9)
    var index: Int = 0

    val cdTimer : CountDownTimer =
        object : CountDownTimer(3500, 100) {
            override fun onFinish() {
                textView6.text = ""
                sec = 3.5f
                textView6.setTextColor(Color.WHITE)

                //占い表示
                var num = Random.nextInt(8)
//                val  ImageButton = view.findViewById<ImageButton>(R.id.imageButton)

                val  ImageButton = imageButton
                when (num) {
                    0 -> { syuruiTextView.text = "フランボワーズ氷"
                        kekkaTextView.text = "甘酸っぱい恋の予感！？"
//                        setBackgroundColor="#F08080"

                        ImageButton.setImageResource(R.drawable.uranai1)}


                    1 -> { syuruiTextView.text = "マスカット氷"
                        kekkaTextView.text = "今日は平和な一日になりそう！"
//                    background="#62D0AA"
                        ImageButton.setImageResource(R.drawable.uranai2)}

                    2 ->{   syuruiTextView.text = "チョコミント氷"
                        kekkaTextView.text = "お仕事、勉強がはかどる一日になりそう！"
//                    background="#00CED1"
                        ImageButton.setImageResource(R.drawable.uranai3)}

                    3 -> {  syuruiTextView.text = "ブルーベリー氷"
                        kekkaTextView.text = "疲れがたまってきているかも。リフレッシュしよう！"
//                    background="#C692F5"
                        ImageButton.setImageResource(R.drawable.uranai4)}

                    4 -> {  syuruiTextView.text = "酒粕氷"
                        kekkaTextView.text = "気分転換をすると良いことあるかも！！"
//                    background="#FFFFFF"
                        ImageButton.setImageResource(R.drawable.uranai5)}

                    5 -> {  syuruiTextView.text = "マロン氷"
                        kekkaTextView.text = "友達とより仲良くなることが起こるかも！？"
//                    background="#FAF0E9"
                        ImageButton.setImageResource(R.drawable.uranai6)}

                    6 ->{   syuruiTextView.text = "オレンジ氷"
                        kekkaTextView.text = "今日は新しい友達が出来るかも！？"
//                    background="#FFBD4C"
                        ImageButton.setImageResource(R.drawable.uranai7)}

                    7 -> {  syuruiTextView.text = "チョコ氷"
                        kekkaTextView.text = "今日は全てがうまくいく予感！"
//                    background="#4F2E24"
                        ImageButton.setImageResource(R.drawable.uranai8)}

                    8 -> {  syuruiTextView.text = "ゴールデンピーチ氷"
                        kekkaTextView.text = "今日は金運アップ！！"
//                    background="#FCEA6B"
                        ImageButton.setImageResource(R.drawable.uranai9)}

                }

            }
            override  fun onTick(millisUntilFinished: Long) {
                sec = sec - 0.1f
//                textView6.text = sec.toString()
                textView6.setTextColor(Color.RED)

                imageButton.setImageResource(imgArr[index])
                index++
                if(index==9)
                    index= 0

                if(sec > 3.0f)
                    textView6.text = "ドゥル"
                else if(sec > 2.0f)
                    textView6.text = "ドゥルル"
                else if(sec > 1.0f)
                    textView6.text = "ドゥルルル"
                else
                    textView6.text = "ドゥルルルル・・"
            }
        }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val layout = inflater.inflate(R.layout.fragment_4, container, false)

        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView6.setOnClickListener {
            cdTimer.start()
        }

        //

    }
}