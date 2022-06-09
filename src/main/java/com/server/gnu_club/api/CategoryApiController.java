package com.server.gnu_club.api;

import com.server.gnu_club.domain.Category;
import com.server.gnu_club.domain.Club;
import com.server.gnu_club.domain.dto.ResultDto;
import com.server.gnu_club.domain.dto.response.CategoryResponseDto;
import com.server.gnu_club.domain.dto.response.ClubPreviewDto;
import com.server.gnu_club.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryApiController {

    private final ClubService clubService;

    /**
     * 클럽 카테고리 리스트 조회
     */
    @GetMapping("/api/categories")
    public ResultDto<List<CategoryResponseDto>> getAllCategories() {
        List<Category> categories = clubService.findAllCategory();
        List<CategoryResponseDto> categoryResponseDtos = categories
                .stream().map(CategoryResponseDto::new).collect(Collectors.toList());

        return new ResultDto<>(categories.size(), categoryResponseDtos);
    }

    /**
     * 특정 카테고리의 동아리 리스트 조회
     */
    @GetMapping("/api/category/{categoryPk}/clubs")
    public ResultDto<List<ClubPreviewDto>> getCategoryClubs(@PathVariable Long categoryPk) {
        List<Club> clubs = clubService.findAllClubByCategoryPk(categoryPk);
        List<ClubPreviewDto> clubPreviewDtos = clubs.stream().map(ClubPreviewDto::new).collect(Collectors.toList());

        return new ResultDto<>(clubPreviewDtos.size(), clubPreviewDtos);
    }
}
