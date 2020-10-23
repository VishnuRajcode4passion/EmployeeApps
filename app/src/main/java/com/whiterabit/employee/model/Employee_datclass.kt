package com.whiterabit.employee.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

/**
 * Created by vishnu on 22/10/20.
 */
@Entity(tableName = "EmployeeBook")
data class Employee_datclass(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "ids")

        var ids:Int,
        @ColumnInfo(name = "id")
        var id: String?,

        @ColumnInfo(name = "name")
        var name: String?,

        @ColumnInfo(name = "username")
        var username: String?,

        @ColumnInfo(name = "email")
        var email: String?,

        @ColumnInfo(name = "profile_image")
        var profile_image: String?,

        @ColumnInfo(name = "address")
        var address: address_dataclass?,

        @ColumnInfo(name = "phone")
        var phone: String?,

        @ColumnInfo(name = "website")
        var website: String?,
        @Ignore
        @ColumnInfo(name = "company")
        var company: company_dataclass?

) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readParcelable(address_dataclass::class.java.classLoader),
                parcel.readString(),
                parcel.readString(),
                parcel.readParcelable(company_dataclass::class.java.classLoader)
               ) {
        }
        constructor():this(0,"","","","","",null,"","",null)


        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(ids)
                parcel.writeString(id)
                parcel.writeString(name)
                parcel.writeString(username)
                parcel.writeString(email)
                parcel.writeString(profile_image)
                parcel.writeParcelable(address, flags)
                parcel.writeString(phone)
                parcel.writeString(website)
                parcel.writeParcelable(company, flags)

        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Employee_datclass> {
                override fun createFromParcel(parcel: Parcel): Employee_datclass {
                        return Employee_datclass(parcel)
                }

                override fun newArray(size: Int): Array<Employee_datclass?> {
                        return arrayOfNulls(size)
                }
        }
}