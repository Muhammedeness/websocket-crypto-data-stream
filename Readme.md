# Kripto Gerçek Zamanlı Veri Akışı (WebSocket)

Binance, Kraken, Coinbase gibi majör kripto para borsalarının WebSocket akışlarına bağlanarak, gerçek zamanlı piyasa verilerini backend'inize yönlendirmenizi sağlayan Spring Boot tabanlı bir referans projesidir.

## Proje Amacı

Bu proje, özellikle aşağıdaki konulara yanıt vermek amacıyla geliştirilmiştir:

-   **Spring Boot** kullanarak güvenilir ve sürekli bir **WebSocket istemcisi** bağlantısı nasıl kurulur?

-   Finansal API'lardan (örn. Borsa) gelen **gerçek zamanlı** veri akışı, backend uygulamasında nasıl yönetilir ve işlenir?


Bu repo, karmaşık finansal veri akışlarını kendi uygulamanıza entegre etmeniz için sağlam bir başlangıç noktası sunar.

## Başlarken

### Gereksinimler

-   **Java Development Kit (JDK) 17+**


### Kurulum Adımları

Projeyi indirmek ve ayağa kaldırmak oldukça basittir.

1.  **Projeyi Klonlayın:** Komut satırınızı açın ve depoyu yerel makinenize klonlayın:

        git clone <project_url>
        cd websocket-crypto-data-stream



2.  **Gereksinimleri Doğrulayın:** Kurulum öncesinde Java ve Maven versiyonlarınızın uygunluğunu kontrol edin:

        java -version 
        mvn -version



    Eğer bu komutlar hata verirse, lütfen ilgili yazılımların kurulu ve PATH'e eklenmiş olduğundan emin olun._

3.  **Port Kontrolü (Opsiyonel):** Proje varsayılan olarak `8080` portunda çalışacaktır. Bu portun başka bir uygulama tarafından kullanılmadığından emin olun.

    | İşletim Sistemi | Kontrol Komutu |
        | --- | --- |
    | **Linux/macOS** | `sudo lsof -i :8080` |
    | **Windows (CMD)** | \`netstat -ano |

4.  **Uygulamayı Çalıştırın:** Proje bağımlılıklarını kurun ve Spring Boot uygulamasını başlatın:

        # Bağımlılıkları derle ve paketi oluştur
        mvn clean install
        
        # Uygulamayı başlat
        mvn spring-boot:run




Uygulama başarıyla başlatıldığında, konsolda "Started Application..." benzeri bir mesaj göreceksiniz. WebSocket bağlantıları arka planda kurulmaya başlayacaktır.


## Katkıda Bulunma

Geri bildirimler ve katkılar her zaman kabul edilir! Bir sorun bildirmek veya yeni bir özellik önermek isterseniz, lütfen bir "Issue" açın veya bir "Pull Request" gönderin.

1.  Depoyu Fork'layın (`https://github.com/KULLANICI_ADINIZ/REPO_ADINIZ.git`)

2.  Yeni bir dal oluşturun (`git checkout -b feature/YeniBorsaEkle`)

3.  Değişikliklerinizi Commit'leyin (`git commit -m 'feat: Kraken WebSocket desteği eklendi'`)

4.  Dalınızı Push edin (`git push origin feature/YeniBorsaEkle`)

5.  Bir Pull Request açın.


## İletişim

**Muhammed Enes Selvi**