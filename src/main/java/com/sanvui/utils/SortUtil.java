package com.sanvui.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: VuiSK
 * @created: 09/12/2021-7:53 PM
 * @mailto: sanvankhanh@gmail.com
 */

public final class SortUtil {

    public static Sort generateSort(List<String> sort, String nameDefaultSort) {

        Sort.Order orderByDefault = null;

        if(StringUtils.isNotBlank(nameDefaultSort)){
            orderByDefault = sortDefault(nameDefaultSort);
        }

        if (CollectionUtils.isEmpty(sort)) {
            return Sort.by(orderByDefault);
        }

        List<Sort.Order> orderBySortField = sort.stream()
                .map(o-> o.startsWith("-") ?
                            Sort.Order.desc(o.substring(1)) : Sort.Order.asc(o))
                .collect(Collectors.toList());

        orderBySortField.add(orderByDefault);

        return Sort.by(orderBySortField);

    }

    private static Sort.Order sortDefault(String nameDefaultSort) {

        return nameDefaultSort.startsWith("-") ?
                Sort.Order.desc(nameDefaultSort.substring(1)) : Sort.Order.asc(nameDefaultSort);
    }
}
