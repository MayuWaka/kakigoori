package app.wakayama.tama.kakigoori

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Diary (
    @PrimaryKey open var id: String = UUID.randomUUID().toString(),
    open var imageUri: String = "",
    open var shopname: String = "",
    open  var memo: String = "",
    open var star:String =""

//    open var createdAt: Date = Date(System.currentTimeMillis())
) : RealmObject()