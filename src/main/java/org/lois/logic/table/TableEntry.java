package org.lois.logic.table;

import org.lois.logic.domain.Value;

import java.util.Map;

public record TableEntry(Map<String, Value> input, Value result) {}
