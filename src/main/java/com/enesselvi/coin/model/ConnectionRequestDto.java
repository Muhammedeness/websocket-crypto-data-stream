package com.enesselvi.coin.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ConnectionRequestDto {

    @NotBlank(message = "{field.not.blank}" )
    private String platform;

    @NotBlank(message = "{field.not.blank}" )
    private String symbol;

    @NotBlank(message = "{field.not.blank}" )
    private String uri;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
