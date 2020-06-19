package app.wakayama.tama.kakigoori

import android.graphics.Color
import android.media.AudioAttributes
import android.media.SoundPool
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

    private  lateinit var mSoundPool: SoundPool
    private  lateinit var mSoundID: Array<Int?>
    private  val mSoundResource =  arrayOf(
        R.raw.drumroll,
        R.raw.triangle
    )

    val cdTimer : CountDownTimer =
        object : CountDownTimer(3300, 100) {
            override fun onFinish() {
                textView6.text = ""
                sec = 3.5f
                textView6.setTextColor(Color.WHITE)

                //占い結果表示
                var num = Random.nextInt(9)
//                val  ImageButton = view.findViewById<ImageButton>(R.id.imageButton)
                val  ImageButton = imageButton
                when (num) {
                    0 -> { syuruiTextView.text = "フランボワーズ氷"
                        kekkaTextView.text = "甘酸っぱい恋の予感！？"
//                        fragment1.setBackgroundResource(R.color.koori1)
//                        fragment2.setBackgroundResource(R.color.koori1)
//                        fragment3.setBackgroundResource(R.color.koori1)
//                        fragment4.setBackgroundResource(R.color.koori1)
                        constraintRayout.setBackgroundResource(R.color.koori1)
                        ImageButton.setImageResource(R.drawable.uranai1)}

                    1 -> { syuruiTextView.text = "マスカット氷"
                        kekkaTextView.text = "今日は平和な一日になりそう！"
//                        fragment1.setBackgroundResource(R.color.koori2)
//                        fragment2.setBackgroundResource(R.color.koori2)
//                        fragment3.setBackgroundResource(R.color.koori2)
//                        fragment4.setBackgroundResource(R.color.koori2)
                        constraintRayout.setBackgroundResource(R.color.koori2)
                        ImageButton.setImageResource(R.drawable.uranai2)}

                    2 ->{   syuruiTextView.text = "チョコミント氷"
                        kekkaTextView.text = "お仕事、勉強がはかどる一日になりそう！"
//                        fragment1.setBackgroundResource(R.color.koori3)
//                        fragment2.setBackgroundResource(R.color.koori3)
//                        fragment3.setBackgroundResource(R.color.koori3)
//                        fragment4.setBackgroundResource(R.color.koori3)
                        constraintRayout.setBackgroundResource(R.color.koori3)
                        ImageButton.setImageResource(R.drawable.uranai3)}

                    3 -> {  syuruiTextView.text = "ブルーベリー氷"
                        kekkaTextView.text = "疲れがたまってきているかも。リフレッシュしよう！"
//                        fragment1.setBackgroundResource(R.color.koori4)
//                        fragment2.setBackgroundResource(R.color.koori4)
//                        fragment3.setBackgroundResource(R.color.koori4)
//                        fragment4.setBackgroundResource(R.color.koori4)
                        constraintRayout.setBackgroundResource(R.color.koori4)
                        ImageButton.setImageResource(R.drawable.uranai4)}

                    4 -> {  syuruiTextView.text = "酒粕氷"
                        kekkaTextView.text = "気分転換をすると良いことあるかも！！"
//                        fragment1.setBackgroundResource(R.color.koori5)
//                        fragment2.setBackgroundResource(R.color.koori5)
//                        fragment3.setBackgroundResource(R.color.koori5)
//                        fragment4.setBackgroundResource(R.color.koori5)
                        constraintRayout.setBackgroundResource(R.color.koori5)
                        ImageButton.setImageResource(R.drawable.uranai5)}

                    5 -> {  syuruiTextView.text = "マロン氷"
                        kekkaTextView.text = "友達とより仲良くなることが起こるかも！？"
//                        fragment1.setBackgroundResource(R.color.koori6)
//                        fragment2.setBackgroundResource(R.color.koori6)
//                        fragment3.setBackgroundResource(R.color.koori6)
//                        fragment4.setBackgroundResource(R.color.koori6)
                        constraintRayout.setBackgroundResource(R.color.koori6)
                        ImageButton.setImageResource(R.drawable.uranai6)}

                    6 ->{   syuruiTextView.text = "オレンジ氷"
                        kekkaTextView.text = "今日は新しい友達が出来るかも！？"
//                        fragment1.setBackgroundResource(R.color.koori7)
//                        fragment2.setBackgroundResource(R.color.koori7)
//                        fragment3.setBackgroundResource(R.color.koori7)
//                        fragment4.setBackgroundResource(R.color.koori7)
                        constraintRayout.setBackgroundResource(R.color.koori7)
                        ImageButton.setImageResource(R.drawable.uranai7)}

                    7 -> {  syuruiTextView.text = "チョコ氷"
                        kekkaTextView.text = "今日は全てがうまくいく予感！"
//                        fragment1.setBackgroundResource(R.color.koori8)
//                        fragment2.setBackgroundResource(R.color.koori8)
//                        fragment3.setBackgroundResource(R.color.koori8)
//                        fragment4.setBackgroundResource(R.color.koori8)
                        constraintRayout.setBackgroundResource(R.color.koori8)
                        ImageButton.setImageResource(R.drawable.uranai8)}

                    8 -> {  syuruiTextView.text = "ゴールデンピーチ氷"
                        kekkaTextView.text = "今日は金運アップ！！"
//                        fragment1.setBackgroundResource(R.color.koori9)
//                        fragment2.setBackgroundResource(R.color.koori9)
//                        fragment3.setBackgroundResource(R.color.koori9)
//                        fragment4.setBackgroundResource(R.color.koori9)
                        constraintRayout.setBackgroundResource(R.color.koori9)
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

                textView6.text = ""

//                if(sec > 3.0f)
//                    textView6.text = "ドゥル"
//                else if(sec > 2.0f)
//                    textView6.text = "ドゥルル"
//                else if(sec > 1.0f)
//                    textView6.text = "ドゥルルル"
//                else
//                    textView6.text = "ドゥルルルル・・"
            }
        }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val layout = inflater.inflate(R.layout.fragment_4, container, false)

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()

        mSoundPool = SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .setMaxStreams(mSoundResource.size)
            .build()

        mSoundID = arrayOfNulls(mSoundResource.size)
        for (i in 0..(mSoundResource.size - 1)){
            mSoundID[i] = mSoundPool.load(requireContext(),mSoundResource[i],0)
        }

        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView6.setOnClickListener {
            mSoundPool.play(mSoundID[0] as Int,1.0F,1.0F,0,0,1.0F)
            cdTimer.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        mSoundPool.release()
    }
}