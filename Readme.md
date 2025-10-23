# Crypto Data Stream

Binance,Kraken,Coinbase gibi platformların websocketlerine bağlanmanıza olanak sağlar.
Web Sockete bağlanıp göndereceğiniz json datası ile gerçek zamanlı veri akışını başlatın

## Proje Hakkında
- Spring Boot ile WEBSOCKET bağlantısı nasıl yapılır ? 
- Real time verileri backendime yönlendirip nasıl yönetebilirim?

   
Bu gibi soruları gidermek amacıyla örnek proje olarak geliştirilmiştir.

## Kurulum


### Gereksinimler

* Java 17+

Adımlar:
   ```bash
   git clone <project_url>
   
   cd websocket-crypto-data-stream
   
   java -version 
   mvn -version (make sure you have java and maven installed) 
   
   sudo lsof -i :8080 (Check if ports are available)
   
   mvn clean install
   mvn spring-boot:run
   
   