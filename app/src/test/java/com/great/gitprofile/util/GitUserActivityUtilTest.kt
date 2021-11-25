package com.great.gitprofile.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class GitUserActivityUtilTest {

    private val validGitId = "Pr-Mann"
    private val emptyGitId = ""
    private val onlySpaceGitId = " "
    private val twoHyphenGitId = "s--g"
    private val startHyphenGitId = "-pr"
    private val endHyphenGitId = "spr-"
    private val specialCharGitId = "sp#r"
    private val numberedGitId = "50762"
    private val numberedCharGitId = "5fgp02"

    @Test
    fun emptyGitId() {
        val result = GitUserActivityUtil.validateGitId(emptyGitId)
        assertThat(result).isFalse()
    }

    @Test
    fun validGitId() {
        val result = GitUserActivityUtil.validateGitId(validGitId)
        assertThat(result).isTrue()
    }

    @Test
    fun onlySpaceGitId() {
        val result = GitUserActivityUtil.validateGitId(onlySpaceGitId)
        assertThat(result).isFalse()
    }

    @Test
    fun twoHyphenGitId() {
        val result = GitUserActivityUtil.validateGitId(twoHyphenGitId)
        assertThat(result).isFalse()
    }

    @Test
    fun startHyphenGitId() {
        val result = GitUserActivityUtil.validateGitId(startHyphenGitId)
        assertThat(result).isFalse()
    }

    @Test
    fun endHyphenGitId() {
        val result = GitUserActivityUtil.validateGitId(endHyphenGitId)
        assertThat(result).isFalse()
    }

    @Test
    fun specialCharGitId() {
        val result = GitUserActivityUtil.validateGitId(specialCharGitId)
        assertThat(result).isFalse()
    }

    @Test
    fun numberedGitId() {
        val result = GitUserActivityUtil.validateGitId(numberedGitId)
        assertThat(result).isTrue()
    }

    @Test
    fun numberedCharGitId() {
        val result = GitUserActivityUtil.validateGitId(numberedCharGitId)
        assertThat(result).isTrue()
    }
}