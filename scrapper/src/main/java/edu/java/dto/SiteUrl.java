package edu.java.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class SiteUrl {
    final public String url;

    @Override
    public String toString() {
        return url;
    }
}
