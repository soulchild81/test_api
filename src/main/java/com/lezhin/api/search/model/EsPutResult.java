package com.lezhin.api.search.model;

import lombok.Data;

@Data
public class EsPutResult {
    private String _index;
    private String _type;
    private String _id;
    private int _version;
    private String result;
    private int _seq_no;
    private int _primary_term;

    private EsShards _shards;
}
