package com.adms.admng.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse<T> {
    private T response;
    private Integer currentPage;
    private Integer totalPages;
    private Integer totalItems;
}
