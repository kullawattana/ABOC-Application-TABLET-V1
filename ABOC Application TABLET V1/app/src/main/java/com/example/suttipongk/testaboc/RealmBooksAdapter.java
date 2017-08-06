package com.example.suttipongk.testaboc;

import android.content.Context;

import com.example.suttipongk.testaboc.Book;
import com.example.suttipongk.testaboc.RealmModelAdapter;

import io.realm.RealmResults;

public class RealmBooksAdapter extends RealmModelAdapter<Book> {

    public RealmBooksAdapter(Context context, RealmResults<Book> realmResults, boolean automaticUpdate) {

        super(context, realmResults, automaticUpdate);
    }
}