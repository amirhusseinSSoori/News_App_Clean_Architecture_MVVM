package com.amirhusseinsoori.newsapp.presentation.util

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class BottomNavHistory(
  private val backStack: ArrayList<Int> = arrayListOf()
) : Parcelable {

  val size: Int
    get() = backStack.size

  val isEmpty: Boolean
    get() = backStack.isEmpty()

  val isNotEmpty: Boolean
    get() = backStack.isNotEmpty()

  fun push(entry: Int) {
    if (backStack.contains(entry)) {
      backStack.run {
        remove(entry)
        add(entry)
      }
    } else backStack.add(entry)
  }

  fun pop(exit: Int) {
    backStack.remove(exit)
  }

  fun current() = backStack.last()

  fun clear() {
    backStack.clear()
  }
}