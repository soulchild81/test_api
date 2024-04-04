package com.lezhin.api.search.result;

import com.lezhin.api.search.model.Product;
import com.lezhin.api.search.model.ProductObject;
import lombok.Data;

import java.util.List;

@Data
public class LotteOnResult {
    private String returnCode;
    private String message;
    private String subMessages;
    private String messageType;
    private int dataCount;
    private ProductObject data;
}
