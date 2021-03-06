package com.fasterxml.jackson.module.kotlin.test.github

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class TestGithub168 {
    @Suppress("UNUSED_PARAMETER")
    class TestClass(@JsonProperty(value = "foo", required = true) foo: String?, val baz: String)

    final val MAPPER: ObjectMapper = jacksonObjectMapper()

    @Test
    fun testIfRequiredIsReallyRequiredWhenNullUsed() {
        val obj = MAPPER.readValue<TestClass>("""{"foo":null,"baz":"whatever"}""")
        assertEquals("whatever", obj.baz)
    }

    @Test(expected = MissingKotlinParameterException::class)
    fun testIfRequiredIsReallyRequiredWhenAbsent() {
        val obj = jacksonObjectMapper().readValue<TestClass>("""{"baz":"whatever"}""")
        assertEquals("whatever", obj.baz)
    }

    @Test
    fun testIfRequiredIsReallyRequiredWhenValuePresent() {
        val obj = MAPPER.readValue<TestClass>("""{"foo":"yay!","baz":"whatever"}""")
        assertNotNull(obj)
        assertEquals("whatever", obj.baz)
    }
}
