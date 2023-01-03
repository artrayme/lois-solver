package org.lois.logic.table.utils;

import org.lois.logic.domain.Value;

import java.util.List;

public interface StateGenerator {

    List<Value> getCurrent();
    List<Value> incrementAndGet();
    void reset();

    boolean hasNext();
}
