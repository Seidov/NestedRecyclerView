package com.sultanseidov.nestedrecyclerview.listener

import android.view.View

abstract class ClickReporter {
    var childPosition:Int = -1
    var parentPosition: Int = -1
    var view: View? = null

    // The user calls this function if any view is Clicked(in parent or child RecyclerView).
    // If it is called  from the Parent RecyclerView adapter, then
    fun onNestedRvClick(typeOfAdapter: String, position: Int, clickedView: View?) {
        if (typeOfAdapter == "Parent") {
            view = clickedView
            parentPosition = position
            onResult(parentPosition, childPosition, clickedView)
            childPosition = -1
            parentPosition = -1
            view = null
        } else if (typeOfAdapter == "Child") {
            view = clickedView
            childPosition = position
            onResult(parentPosition, childPosition, clickedView)
            childPosition = -1
            parentPosition = -1
            view = null
        }
    }
    // This function is called from the onItemClick() function in the Activity/Fragment that uses
    // an instance of this class(ClickReporter). It gets called irrespective if the user clicked on
    // a view in Parent RecyclerView or Child RecyclerView.
    // But we only need the information from the call to this function if the
    // user clicked on a View in the Child RecyclerView as it tells us which position in the Parent
    // RecyclerView was clicked.
    fun onNestedRvItemClick(position: Int) {
        parentPosition = position
    }

    // This is the function the user has to over ride in their Activity/Fragment where they will
    // be using ClickReporter. It lets them know which position in the Parent RecyclerView was clicked,
    // position in Child RecyclerView and specific view in the layout that was clicked.
    // If the clicked View is only present in the Parent RecyclerView then childPos will be -1.
    abstract fun onResult(parentPos: Int, childPos: Int, viewClicked: View?)



}