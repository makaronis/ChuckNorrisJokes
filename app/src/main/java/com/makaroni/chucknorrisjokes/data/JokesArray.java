package com.makaroni.chucknorrisjokes.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JokesArray {
    @SerializedName("value")
    private ArrayList<Joke> list;

    public List<Joke> getList() {
        return list;
    }

}
