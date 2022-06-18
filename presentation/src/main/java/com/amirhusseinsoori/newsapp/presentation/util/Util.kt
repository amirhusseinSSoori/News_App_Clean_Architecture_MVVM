package com.amirhusseinsoori.newsapp.presentation.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SearchView
import com.google.gson.Gson

inline fun EditText.onTextChange(crossinline listener: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            listener(s.toString())
        }
    })
}

inline fun SearchView.onQueryTextListener(crossinline listener : (String) -> Unit){
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(query: String?) = true

        override fun onQueryTextChange(newText: String?): Boolean {
            listener(newText.orEmpty())
            return true
        }
    })

}

inline fun SearchView.onQueryTextSubmitListener(crossinline listener : (String) -> Unit){
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(query: String?): Boolean {
            listener(query.orEmpty())
            return true
        }

        override fun onQueryTextChange(newText: String?) = true
    })

}
 fun String.Companion.empty() = ""
inline fun <reified T> getArgByGson(json: String): T {
    return Gson().fromJson(
        json,
        T::class.java
    )
}
inline fun <reified T> sendArgByGson(input: T): String {
    return Gson().toJson(
        input
    )
}