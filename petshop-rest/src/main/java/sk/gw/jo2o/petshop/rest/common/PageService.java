package sk.gw.jo2o.petshop.rest.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    public PageRequest createPageRequest(Integer pageIndex, Integer pageSize) {
        return PageRequest.of(processPageIndex(pageIndex), processPageSize(pageSize));
    }

    private int processPageIndex(Integer pageIndex) {
        Integer result = pageIndex;
        if (pageIndex == null || pageIndex < 0) {
            result = 0;
        }
        return result;
    }

    private int processPageSize(Integer pageSize) {
        Integer result = pageSize;
        if (pageSize == null || pageSize < 0) {
            result = Integer.MAX_VALUE;
        }
        return result;
    }

}
