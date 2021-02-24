package com.fasterxml.jackson.module.kotlin.test

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class DisableKotlinDefaultsTest {

  private val mapper = JsonMapper.builder()
    .addModule(KotlinModule(enableDefaultsSupport = false))
    .build()

  @Test
  fun testDisableDefaults() {
    val json = """{"title":"foo"}"""
    val stateObj = mapper.readValue<KotlinClassWithDefaults>(json)
    assertThat(stateObj.title, equalTo("foo"))
    assertThat(stateObj.description, equalTo(null))
  }

  data class KotlinClassWithDefaults(val title: String, val description: String? = "foo bar")
}