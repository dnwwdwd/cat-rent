package com.qww.carrent.model.request;

import com.qww.carrent.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class CarCategoryQueryRequest extends PageRequest implements Serializable {
    private Integer minPrice;

    private Integer maxPrice;
}
