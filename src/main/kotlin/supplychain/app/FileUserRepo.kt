package supplychain.app

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.File

class FileUserRepo: UserRepoInterface {
    private val mapper = jacksonObjectMapper()
    private val resource = this::class.java.classLoader.getResource("users.json")
    private val file = File(resource?.path ?: "No file")
    private val jsonContent = file.readText()
    private val usersFromJson: List<UserDataModel> = mapper.readValue(jsonContent, object : TypeReference<List<UserDataModel>>() {})


    override fun fetchCompanyThatUserBelongsTo(userID: String): String {
        val user = usersFromJson.find { it.id == userID }
        return user?.companyId ?: "Error Retrieving companyId"
    }
}

interface UserRepoInterface {
    fun fetchCompanyThatUserBelongsTo(userID: String): String

}

