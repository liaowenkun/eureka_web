package com.rutrunJson;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果类
 * @param <T>
 */
@Data
public class XHPageResult<T> implements Serializable {

    //当前第几页(查询页码下标)
    //private int pageIndex = 0;
    //每页显示数量
    //private int pageSize = 12;

    //总条数
    private Long total;
    //总页数 (少用)
    private Long pageCount;

    private List<T> rows;

    private XHPageResult() {
    }

    public XHPageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public XHPageResult(Long total, Long pageCount, List<T> rows) {
        this.total = total;
        this.pageCount = pageCount;
        this.rows = rows;
    }
}
