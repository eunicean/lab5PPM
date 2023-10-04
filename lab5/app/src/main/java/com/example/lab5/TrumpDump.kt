package com.example.lab5

data class TrumpDump(
    val _embedded: Embedded,
    val _links: LinksXX,
    val appeared_at: String, // 2016-07-06T12:31:27.000Z
    val created_at: String, // 2016-11-20T01:39:11.778Z
    val quote_id: String, // UHbkHmk9S-KMcDg1mLeeEg
    val tags: List<String>,
    val updated_at: String, // 2016-11-20T01:39:11.778Z
    val value: String // Crooked Hillary Clinton lied to the FBI and to the people of our country. She is sooooo guilty. But watch, her time will come!
)