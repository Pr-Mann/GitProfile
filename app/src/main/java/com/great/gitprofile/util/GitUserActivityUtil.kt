package com.great.gitprofile.util

import java.util.regex.Pattern.compile

object GitUserActivityUtil {

    val pattern = compile("^[a-zA-Z0-9]{1,}[-]?[a-zA-Z0-9]*")
    fun validateGitId(id: String): Boolean {
        if (id.trim() == "") {
            return false
        }
        if (id.startsWith("-") || id.endsWith("-")) {
            return false
        }
        return pattern.matcher(id).matches()
    }
}