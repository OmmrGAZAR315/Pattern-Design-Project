package org.example.dpproject.app.Services;

import org.example.dpproject.DB.QBResults;
import org.example.dpproject.app.Helpers.HttpResponse;
import org.example.dpproject.app.Repositories.CommentRepository;

public class CommentService {
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
}
