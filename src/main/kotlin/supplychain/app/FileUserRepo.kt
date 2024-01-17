package supplychain.app

class FileUserRepo: UserRepoInterface {
    override fun fetchCompanyThatUserBelongsTo(userID: String): String {
        if (userID == "ZU123") {
            return "ZC789"
        }
        TODO()
    }


}

interface UserRepoInterface {
    fun fetchCompanyThatUserBelongsTo(userID: String): String

}