package dev.shreyasayyengar.util

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.model.Filters
import com.mongodb.client.model.ReplaceOptions
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoCollection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.bson.Document

object MongoService {
    val pointsCollection: MongoCollection<Document>

    init {
        val mongoClient = MongoClient.create(
            MongoClientSettings.builder()
                .applyConnectionString(ConnectionString(Credentials.MONGO_URL))
                .build()
        )
        pointsCollection = mongoClient.getDatabase("customers").getCollection("customers")
    }

    suspend fun addPoint(snowflakeId: Long, amount: Int) = withContext(Dispatchers.IO) {
        val document = Document.parse(Json.encodeToString(Points.serializer(), Points(snowflakeId, amount)))

        pointsCollection.replaceOne(
            Filters.eq("_id", snowflakeId.toString()),
            document,
            ReplaceOptions().upsert(true)
        )
    }

    suspend fun getPoints(snowflakeId: Long): Int? = withContext(Dispatchers.IO) {
        val document = pointsCollection.find(Filters.eq("_id", snowflakeId.toString())).firstOrNull()
        if (document == null) return@withContext null
        return@withContext Json.decodeFromJsonElement(Points.serializer(), Json.parseToJsonElement(document.toJson())).amount
    }

    @Serializable
    class Points(
        @SerialName("_id")
        val snowflakeId: Long,
        val amount: Int
    )
}