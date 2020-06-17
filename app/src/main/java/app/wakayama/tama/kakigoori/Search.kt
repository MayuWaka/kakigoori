package app.wakayama.tama.kakigoori

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Search (
        @PrimaryKey open var id: String = UUID.randomUUID().toString(),
        open var searchArea: String = ""
    ) : RealmObject()
