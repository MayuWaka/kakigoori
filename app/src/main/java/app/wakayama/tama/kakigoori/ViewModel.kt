package app.wakayama.tama.kakigoori

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {
    val memo = MutableLiveData<String>()
    val newMemo = MutableLiveData<String>()
    val updateItem = MutableLiveData<Shop?>()
    val updateMemo = MutableLiveData<String>()
    val deleteId = MutableLiveData<Int>()

    fun clearMemo() {
        memo.value = ""
    }

    fun clearUpdate() {
        updateItem.value = null
        updateMemo.value = ""
    }

    enum class KEY {
        REALM_ID
    }
}