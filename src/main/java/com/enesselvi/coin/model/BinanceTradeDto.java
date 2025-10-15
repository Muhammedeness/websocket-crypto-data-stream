package com.enesselvi.coin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
{
    wss://stream.binance.com:9443/ws/btcusdt@trade this websocket returns this json data:
 | Alan | Tür                    | Açıklama                                                                                                            |
 | ---- | ---------------------- | ------------------------------------------------------------------------------------------------------------------- |
 | `e`  | String                 | **Event type** → Olayın türü. Burada `"trade"` yani bu mesaj bir “işlem” olayıdır.                                  |
 | `E`  | Number (timestamp, ms) | **Event time** → Olayın Binance sunucusunda gerçekleştiği zaman (Unix epoch milliseconds).                          |
 | `s`  | String                 | **Symbol** → İşlem yapılan parite. Örneğin `"BTCUSDT"`.                                                             |
 | `t`  | Number                 | **Trade ID** → Binance sisteminde bu işleme verilen benzersiz kimlik numarası.                                      |
 | `p`  | String (decimal)       | **Price** → İşlemin gerçekleştiği fiyat (örneğin `"111140.01"` USDT).                                               |
 | `q`  | String (decimal)       | **Quantity** → İşlemde el değiştiren coin miktarı (örneğin `"0.00262"` BTC).                                        |
 | `T`  | Number (timestamp, ms) | **Trade time** → İşlemin gerçekten gerçekleştiği zaman. Genellikle `E` ile aynıdır ama küçük fark olabilir.         |
 | `m`  | Boolean                | **Is buyer the market maker?** → `true` ise alıcı market maker’dır. (Yani limit emrini veren kişi alıcı tarafıdır.) |
 | `M`  | Boolean                | **Ignore field** → Binance dokümantasyonuna göre bu alan **her zaman true** olur ve **yoksayılabilir**.             |

 Example JSON Data:

        "e": "trade",
        "E": 1760444202088,
        "s": "BTCUSDT",
        "t": 5337029739,
        "p": "111140.01000000",
        "q": "0.00262000",
        "T": 1760444202088,
        "m": true,
        "M": true
}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BinanceTradeDto {
    @JsonProperty("e")
    private  String     e_eventType;

    @JsonProperty("E")
    private  Long     E_number;

    @JsonProperty("s")
    private  String       s_symbol;

    @JsonProperty("t")
    private  Long       t_tradeId;

    @JsonProperty("p")
    private  BigDecimal p_price;

    @JsonProperty("q")
    private  BigDecimal q_quantity;

    @JsonProperty("T")
    private  Long       T_tradeTime;

    @JsonProperty("m")
    private  Boolean    m_marketMaker;


    public String getE_eventType() {
        return e_eventType;
    }
    public void setE_eventType(String e_eventType) {
        this.e_eventType = e_eventType;
    }


    public String getS_symbol() {
        return s_symbol;
    }
    public void setS_symbol(String s_symbol) {
        this.s_symbol = s_symbol;
    }


    public Long getE_number() {
        return E_number;
    }
    public void setE_number(Long e_number) {
        E_number = e_number;
    }


    public Long getT_tradeId() {
        return t_tradeId;
    }
    public void setT_tradeId(Long t_tradeId) {
        this.t_tradeId = t_tradeId;
    }


    public BigDecimal getP_price() {
        return p_price;
    }
    public void setP_price(BigDecimal p_price) {
        this.p_price = p_price;
    }


    public BigDecimal getQ_quantity() {
        return q_quantity;
    }
    public void setQ_quantity(BigDecimal q_quantity) {
        this.q_quantity = q_quantity;
    }


    public Long getT_tradeTime() {
        return T_tradeTime;
    }
    public void setT_tradeTime(Long t_tradeTime) {
        T_tradeTime = t_tradeTime;
    }

    public Boolean getM_marketMaker() {
        return m_marketMaker;
    }
    public void setM_marketMaker(Boolean m_marketMaker) {
        this.m_marketMaker = m_marketMaker;
    }
    @Override
    public String toString() {
        return "BinanceTradeDto{" +
                "e_eventType='" + e_eventType + '\'' +
                ", E_number=" + E_number +
                ", s_symbol='" + s_symbol + '\'' +
                ", t_tradeId=" + t_tradeId +
                ", p_price=" + p_price +
                ", q_quantity=" + q_quantity +
                ", T_tradeTime=" + T_tradeTime +
                ", m_marketMaker=" + m_marketMaker +
                '}';
    }

}
