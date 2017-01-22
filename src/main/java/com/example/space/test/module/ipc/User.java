package com.example.space.test.module.ipc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by space on 2017/1/18.
 */

public class User implements Parcelable {
    public int userID;
    public String userName;
    public boolean isMale;

    public User(int userID, String userName, boolean isMale) {
        this.userID = userID;
        this.userName = userName;
        this.isMale = isMale;
    }

    protected User(Parcel in) {

        userID=in.readInt();
        userName=in.readString();
        isMale=in.readInt()==1;
        in.readParcelable(Thread.currentThread().getContextClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     * @see #CONTENTS_FILE_DESCRIPTOR
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userID);
        dest.writeString(userName);
        dest.writeInt(isMale ? 1 : 0);
    }
}
