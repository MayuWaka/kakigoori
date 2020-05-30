package app.wakayama.tama.kakigoori

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Shop (
    @PrimaryKey open var id: String = UUID.randomUUID().toString(),
    open var imageId: Int = 0,
  //  open var content: String = "",
    open var shopname: String = "",
    open var address: String = ""
  //  open var createdAt: Date = Date(System.currentTimeMillis())
    ) : RealmObject()






