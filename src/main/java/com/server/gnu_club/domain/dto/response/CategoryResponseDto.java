package com.server.gnu_club.domain.dto.response;

import com.server.gnu_club.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class CategoryResponseDto {

    private Long categoryPk;
    private String categoryName;

    public CategoryResponseDto(Category category) {
        this.categoryPk = category.getPk();
        this.categoryName = category.getCategoryName();
    }
}
