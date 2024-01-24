package supplychain.app.repo

interface UserRepoInterface {
    fun fetchCompanyThatUserBelongsTo(userID: String): String

}