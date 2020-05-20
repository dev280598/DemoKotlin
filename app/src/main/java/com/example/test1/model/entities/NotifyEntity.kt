package nguyentrandroid.a.hhll.data.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.test1.constant.DataConverter
import com.example.test1.model.Fi101
import com.example.test1.model.Fi102


data class ItemNotify(
    val _id: String,
    val _index: String,
    val _source: source,
    val _type: String,
    val sort: List<Double>
)

data class source(
        val fi101: List<Fi101>,
        val mid_: String,
        val iv107: String,
        val iv104: String,
        val iv105: Int,
        val id: String,
        val type: String,
        val user: String,
        val `when`: String,
        val fi102: List<Fi102>,
        val seen: Int,
        val clicked: Int
)

@Entity(tableName = "noti_table")
@TypeConverters(DataConverter::class)
data class ItemNotifyDB(
    @PrimaryKey @ColumnInfo(name = "id") val _id: String,
    @ColumnInfo(name = "index") val _index: String,
    @ColumnInfo(name = "type") val _type: String,
    @ColumnInfo(name = "sort") val _sort: List<Double>? = null,
    @ColumnInfo(name = "source") val source: source?
)


