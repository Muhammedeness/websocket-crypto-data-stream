package com.enesselvi.coin.service.impl;

import com.enesselvi.coin.model.BinanceTradeDto;
import com.enesselvi.coin.service.ParserService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ParserServiceImpl implements ParserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParserServiceImpl.class);

    private final ObjectMapper objectMapper;
    public ParserServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    /**
     * Parses a JSON string payload from the Binance WebSocket into a {@link BinanceTradeDto} object.
     * This method uses Jackson's {@link com.fasterxml.jackson.databind.ObjectMapper} to convert
     * the incoming JSON message into a strongly-typed DTO for further processing in the application.
     * Example of expected JSON payload:
     * {
     *   "e": "trade",
     *   "E": 1760444202088,
     *   "s": "BTCUSDT",
     *   "t": 5337029739,
     *   "p": "111140.01000000",
     *   "q": "0.00262000",
     *   "T": 1760444202088,
     *   "m": true,
     *   "M": true
     * }
     * @param payload the JSON string received from the Binance WebSocket
     * @return a {@link BinanceTradeDto} object representing the trade data
     * @throws com.fasterxml.jackson.core.JsonProcessingException if the JSON payload cannot be parsed
     */

    @Override
    public BinanceTradeDto ParseJsonToDto(String payload){
        try {
            LOGGER.info("Parsing JSON : {} to BinanceTradeDto" ,  payload);
            return objectMapper.readValue(payload , BinanceTradeDto.class);

        } catch (JsonProcessingException e) {
            LOGGER.error("Failed to parse json : {} | Returned null",payload , e);
            return  null;
        }
    }

}
