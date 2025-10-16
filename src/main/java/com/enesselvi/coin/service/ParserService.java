package com.enesselvi.coin.service;

import com.enesselvi.coin.model.BinanceTradeDto;

public interface ParserService {

    /**
     * Parse json data to BinancaTradeDto
     * payload : websocket message  json data
     */
     BinanceTradeDto parseJsonToDto(String payload);
}
