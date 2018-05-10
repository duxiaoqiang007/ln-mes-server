package com.smu.vaan.model;

import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.Objects;

/**
 * Created by vaan on 2017/4/14.
 * 带有额外信息的分页列表
 */
public class PageWithExtra<T> {
    private Page<T> page;
    private Map<String, Object> extra;

    public PageWithExtra(Page<T> page, Map<String, Object> extra) {
        this.page = page;
        this.extra = extra;
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }


    public Map<String, Object> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageWithExtra)) return false;
        PageWithExtra<?> that = (PageWithExtra<?>) o;
        return Objects.equals(page, that.page) &&
                Objects.equals(extra, that.extra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, extra);
    }
}
