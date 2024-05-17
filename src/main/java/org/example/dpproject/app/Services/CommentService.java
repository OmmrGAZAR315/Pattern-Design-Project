package org.example.dpproject.app.Services;

import org.example.dpproject.DB.QBResults;
import org.example.dpproject.app.Helpers.HttpResponse;
import org.example.dpproject.app.DTOs.CommentDto;
import org.example.dpproject.app.Models.Comment;
import org.example.dpproject.app.Models.Post;
import org.example.dpproject.app.Proxy.Cacheable;
import org.example.dpproject.app.Repositories.CommentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentService implements Cacheable {
    private final CommentRepository commentRepository;

    public CommentService() {
        this.commentRepository = new CommentRepository();
    }

    public QBResults getAllComments() {
        QBResults results = commentRepository.getAllComments();
        if (results.getStatusCode() != HttpResponse.OK.getCode())
            return results.setCustom_message("no comments found");
        return results;
    }

    public QBResults createComment(CommentDto commentDto) {
        QBResults results = commentRepository.createComment(commentDto);
        if (results.getStatusCode() != HttpResponse.CREATED.getCode())
            return results.setCustom_message("comment not created");
        return results;
    }

    public Map<Integer, Object> getRecentPostsComments(int i) {
        List<Object> posts = new PostService().getRecentPosts(i);
        Map<Integer, Object> comments = new HashMap<>();
        if (posts != null) {
            for (Object post : posts) {
                Comment[] commentsList = ((Post) post).comments();
                if (commentsList != null)
                    comments.put(((Post) post).getId(), commentsList);
                else
                    return null;
            }
        } else
            return null;
        return comments;
    }

    public Comment[] getLastPostComments() {
        Object post = new PostService().getLastPost();
        if (post == null)
            return null;
        return ((Post) post).comments();
    }

    @Override
    public List<Object> getCache() {
        return null;
    }

    @Override
    public Map<Integer, Object> getMapCache() {
        return null;
    }
}
