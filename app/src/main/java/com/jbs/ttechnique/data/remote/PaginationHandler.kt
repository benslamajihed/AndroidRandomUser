package com.jbs.ttechnique.data.remote

import android.os.Parcel
import android.os.Parcelable
import timber.log.Timber

class PaginationHandler : Parcelable {
    var continuationPage: Int = 0
        private set

    constructor() {}
    protected constructor(parcel: Parcel) {
        continuationPage = parcel.readInt()
    }

    fun resetToken() {
        continuationPage = 0
    }

    fun setContinuationPage(continuationToken: Int) {
        Timber.d("page: $continuationToken")
        this.continuationPage = continuationToken
    }

    fun incContinuationPage() {
        this.continuationPage += 1
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(continuationPage)
    }

    companion object CREATOR : Parcelable.Creator<PaginationHandler> {
        override fun createFromParcel(parcel: Parcel): PaginationHandler {
            return PaginationHandler(parcel)
        }

        override fun newArray(size: Int): Array<PaginationHandler?> {
            return arrayOfNulls(size)
        }
    }
}
