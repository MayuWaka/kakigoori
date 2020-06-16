package app.wakayama.tama.kakigoori

import android.net.Uri
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Diary (
    @PrimaryKey open var id: String? = UUID.randomUUID().toString(),
    open var shopname: String = "",
    open var imageUri: String = "",
    open var memo: String = "",
    open var star:Float = 0.0F,
    open var createdAt: Date = Date(System.currentTimeMillis())
) : RealmObject()