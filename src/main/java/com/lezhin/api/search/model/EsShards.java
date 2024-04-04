package com.lezhin.api.search.model;

import lombok.Data;

@Data
public class EsShards {
    private int total;
    private int successful;
    private int failed;
}
