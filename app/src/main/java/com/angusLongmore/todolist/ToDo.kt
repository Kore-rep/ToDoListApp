package com.angusLongmore.todolist


// A class representing each individual ToDo item
// Will be created and destroyed as needed
// There is no logic involved here, so this is a data class
// Therefore no curly braces are needed
data class ToDo (
        val title: String,
        var isChecked: Boolean = false
)

