package com.wyjax.sse;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Stock {
    private String code;
    private int value;
}
