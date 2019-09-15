package com.makaroni.chucknorrisjokes.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Joke implements Parcelable {
    @SerializedName("joke")
    private String body;

    Joke(Parcel parcel) {
        this.body = parcel.readString();
    }

    public String getBody() {
        String format;
        if (body.contains("&quot;")) {
            format = body.replace("&quot;", "'");
            return format;
        }

        return body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(body);
    }

    public static final Parcelable.Creator<Joke> CREATOR = new Parcelable.Creator<Joke>() {

        @Override
        public Joke createFromParcel(Parcel source) {
            return new Joke(source);
        }

        @Override
        public Joke[] newArray(int size) {
            return new Joke[size];
        }
    };
}
