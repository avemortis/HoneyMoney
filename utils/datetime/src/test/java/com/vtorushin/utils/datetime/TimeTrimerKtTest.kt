package com.vtorushin.utils.datetime

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.format.DateTimeParseException

internal class TimeTrimerKtTest {
    @Test
    fun `WHEN datetimeAsString 2023-06-27T13-44-18-088Z EXPECT 27-06-2023`() {
        val expected = "27.06.2023"
        val actual = trimTime("2023-06-27T13:44:18.088Z")
        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN datetime wrongtext EXPECT date time parse exception`() {
        assertThrows<DateTimeParseException> {
            trimTime("wrondtext")
        }
    }
}