package com.spring.guideance.post.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseSimpleArticleDto {
    private Long articleId;
    private String title;
    private String contents;
    private String authorName;
    private int likesCount;
    private int commentCount;

    public ResponseSimpleArticleDto(Long articleId, String title, String contents, String authorName, int likesCount, int commentCount) {
        this.articleId = articleId;
        this.title = title;
        this.contents = contents;
        this.authorName = authorName;
        this.likesCount = likesCount;
        this.commentCount = commentCount;
    }
}
