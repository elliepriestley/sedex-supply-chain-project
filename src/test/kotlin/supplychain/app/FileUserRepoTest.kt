package supplychain.app

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FileUserRepoTest {

    @Test
    fun `Test that when fetchCompanyThatUserBelongsTo is called, usersjson file is read to return correct company id`() {

        val fileUserRepo = FileUserRepo()
        assertEquals(fileUserRepo.fetchCompanyThatUserBelongsTo("ZU124"), "ZC790")
        assertEquals(fileUserRepo.fetchCompanyThatUserBelongsTo("ZU123"), "ZC789")

    }
}