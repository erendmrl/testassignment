### Testleri Çalıştırma
- **Browser ve driver path'leri /config altındaki config.properties dosyasından değiştirin. 'browser' parametresi tamamen küçük harflerden oluşmalıdır,** örn. browser = chrome. Şu an desteklenen browser'lar: Chrome ve Firefox
- **Tek feature dosyası vardır:** testassignment.feature. Tüm testler bu feature üzerinden çalışmaktadır. Ayrıca src/test/java/restapitests/APITests.java class'ındaki API testleri bağımsız olarak da çalışabilir. 
### Dosya lokasyonları
- Proje loglama yapmakta ve dosyaya yazabilmektedir ayrıca test dataları json dosyasından okunmaktadır.
- Log dosyası ve output dosyası proje altına çıkmaktadır. Log dosyası lokasyonu: src/main/resources/logfile.log
- Genel amaçlı txt dosyası lokasyonu: src/main/resources/outputfile.txt
- Test datalarının json şeklinde tutulduğu dosya lokasyonu: testdata/testdata.json. Halihazırdaki dosyada gerekli tüm bilgiler vardır.
