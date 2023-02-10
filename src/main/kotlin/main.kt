data class Post(
    val id: Int? = null,
    val authorId: Long? = null,
    val authorName: String? = null,
    val content: String? = null,
    val published: Long? = null,
    val date: Int,
    val repost: String? = null,
    val search: String? = null,
    val checkCopyrightLink: String? = null,
    var likes: Likes
) {

}

data class Comment(
    val count: Long,
    val text: String,
    val id: Int,
    val canPost: Boolean,
    val groupsCanPost: Boolean,
    val canClose: Boolean,
    val canOpen: Boolean
) {

}

class Likes(likes: Int) {
    var likes = likes
        set(value) {
            if (value < 0) {
                return
            }
            field = value
        }
}

object WallService {
    private var posts = emptyArray<Post>()
    private var standartPostId: Int = 1
    private var standartCommentID: Int = 1
    private var comments = emptyArray<Comment>()

    fun add(post: Post): Post {
        posts += post.copy(id = standartPostId)
        standartPostId++
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, item) in posts.withIndex()) {
            if (item.id == post.id) {
                posts[index] = post.copy(id = post.id, date = post.date)
                return true
            }
        }
        return false
    }

    fun newComment(comment: Comment, postId: Int): Comment {
        for (post in posts) {
            if (post.id == postId) {
                standartCommentID++
                val newComment = comment.copy(id = standartCommentID)
                comments += newComment
                //println("Комментарий добавлен")
                return comments.last()
            }
        }
        throw NoPost("Пост удален или не существует")
    }
}

fun main() {

}


