package supplychain.app

class UserRepo {
    fun fetchCompanyThatUserBelongsTo(userID: String): String {
        if (userID == "ZU123") {
            return "ZC789"
        }
        TODO()
    }


}