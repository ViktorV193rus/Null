import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {
    @Before
    fun clearBeforeTest() {

    }

    @Test
    fun addPost() {
        val post1 = Post(1, 100, "Ivan", "text", 100, 2021, "Liza",
            search = "",
            checkCopyrightLink = "",
            likes = Likes(10)
        )
        val expected = 2
        WallService.add(post1)
        val result = WallService.add(post1).id
        assertEquals(expected, result)
    }

    @Test
    fun update() {
        val service = WallService
        service.add(Post(2, 100, "Viktor", "text", 100, 2022, "Liza", "", "", Likes(1)))
        service.add(Post(3, 101, "Viktor", "text", 101, 2023, "Liza", "", "", Likes(12)))
        val update = Post(3,101, "Viktor", "text", 101, 2023, "Liza", "", "", Likes(12))

        val result = service.update(update)

        assertTrue(result)
    }
    @Test
    fun newComment() {
        val postComment = Post(1, 100, "Ivan", "text", 100, 2021, "Liza",
            search = "",
            checkCopyrightLink = "",
            likes = Likes(10)
        )
        val commentForTest1 = Comment(101, "yra", id = 1, canPost = false, groupsCanPost = false, canClose = false, canOpen = false)
        val expected = "yra"
        WallService.add(postComment)
        WallService.newComment(commentForTest1, 1)
        val result = commentForTest1.text
        assertEquals(expected, result)
    }

    @Test
    fun noPost() {
        val post1 = Post(1, 100, "Ivan", "text", 100, 2021, "Liza",
            search = "",
            checkCopyrightLink = "",
            likes = Likes(10)
        )
        WallService.add(post1)
        val commentTest2 = Comment(101, "text", id = 1, canPost = false, groupsCanPost = false, canClose = false, canOpen = false)
        WallService.newComment(commentTest2, 1)
    }
}
