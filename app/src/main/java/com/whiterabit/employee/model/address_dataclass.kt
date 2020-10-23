package com.whiterabit.employee.model


import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


/**
 * //created by vishnu
 */
@Entity(tableName = "address")
data class address_dataclass(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ids")
    var ids:Int,

    @ColumnInfo(name = "street")
    var street: String?,

    @ColumnInfo(name = "suite")
    var suite: String?,

    @ColumnInfo(name = "city")
    var city: String?,
    @ColumnInfo(name = "zipcode")
    var zipcode: String?


)
    : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
            ) {
    }

    constructor():this(0,"","","","")



    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ids)
        parcel.writeString(street)
        parcel.writeString(suite)
        parcel.writeString(city)
        parcel.writeString(zipcode)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "address_dataclass(ids='$ids',street='$street', suite='$suite', city='$city', zipcode=$zipcode')"
    }

    companion object CREATOR : Parcelable.Creator<address_dataclass> {
        override fun createFromParcel(parcel: Parcel): address_dataclass {
            return address_dataclass(parcel)
        }

        override fun newArray(size: Int): Array<address_dataclass?> {
            return arrayOfNulls(size)
        }
    }

}