package dev.nanosync.wowonlinetools.entity;

import lombok.Data;

import java.util.Map;

@Data
public class ItemEntity {
    private Long id;
    private String name;
    private Long required_level;
    private Long level;
    private Long sell_price;
    private Map<String, String> quality;
}
