package com.devnweyi.osdatasetup.room.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class CategoryEntity (

    @PrimaryKey(autoGenerate = true)
    var id:Int,

    var categoryName:String

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(categoryName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CategoryEntity> {
        override fun createFromParcel(parcel: Parcel): CategoryEntity {
            return CategoryEntity(parcel)
        }

        override fun newArray(size: Int): Array<CategoryEntity?> {
            return arrayOfNulls(size)
        }
    }

}