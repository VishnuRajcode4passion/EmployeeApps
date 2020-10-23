package com.whiterabit.employee.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * //created by vishnu
 */
@Entity(tableName = "company")
data class company_dataclass(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ids")

    var ids:Int,
    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "catchPhrase")
    var catchPhrase: String?,

    @ColumnInfo(name = "bs")
    var bs: String?


)
    : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
            ) {
    }

    constructor():this(0,"","","")





    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ids)
        parcel.writeString(name)
        parcel.writeString(catchPhrase)
        parcel.writeString(bs)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "company_dataclass(ids='$ids',name='$name', catchPhrase='$catchPhrase', bs=$bs')"
    }


    companion object CREATOR : Parcelable.Creator<company_dataclass> {
        override fun createFromParcel(parcel: Parcel): company_dataclass {
            return company_dataclass(parcel)
        }

        override fun newArray(size: Int): Array<company_dataclass?> {
            return arrayOfNulls(size)
        }
    }

}