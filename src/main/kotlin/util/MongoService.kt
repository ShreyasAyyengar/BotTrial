package dev.shreyasayyengar.util

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.kotlin.client.coroutine.MongoClient

object MongoService {
    init {
        val mongoClient = MongoClient.create(
            MongoClientSettings.builder()
                .applyConnectionString(ConnectionString(CommissionsManager.Credentials.MONGO_URL))
                .serverApi(ServerApi.builder().version(ServerApiVersion.V1).build())
                .build()
        )
        customerMongoCollection = mongoClient.getDatabase("customers").getCollection("customers")
    }

    suspend fun addPoint(snowflakeId: Long, amount: Int) {

    }

    suspend fun getPoints(snowflakeId: Long): Int {
        return 0
    }
}