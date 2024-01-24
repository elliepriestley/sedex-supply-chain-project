package supplychain.app.repo

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import supplychain.app.repo.FileUserRepo


class FileUserRepoTest {

    @Test
    fun `Test that when fetchCompanyThatUserBelongsTo is called, usersjson file is read to return correct company id`() {

        val fileUserRepo = FileUserRepo()
        assertEquals(fileUserRepo.fetchCompanyThatUserBelongsTo("ZU124"), "ZC790")
        assertEquals(fileUserRepo.fetchCompanyThatUserBelongsTo("ZU123"), "ZC789")

    }

    @Test
    fun `Test that when fetchCompanyThatUserBelongsTo is called, when user doesn't exist, exception is thrown`() {
        val fileUserRepo = FileUserRepo()

        assertThrows<Exception> {
            fileUserRepo.fetchCompanyThatUserBelongsTo("nouser")
        }
        assertThrows<Exception> {
            fileUserRepo.fetchCompanyThatUserBelongsTo("129836")
        }

    }
}