## Rest API Test Automation

Bu projede, BDD yaklaşımı ile bir API test otomasyonu gerçekleştirilmiştir.

Java programlama dili ile oluşturulan bir Maven projesidir.

Teslerin yazımı için Rest Assured, gerçeklenmesi için ise TestNG kütüphanesi kullanılmıştır.

***
Spring Boot ile bir Rest API yazılmıştır. Testler, bu API üzerinden gerçekleştirilmiştir.

API, aşağıdaki endpoint'lerden oluşmaktadır : 

**GET** (allGrocery) - *Tüm detayın çekilmesi*

**GET** (/allGrocery/{name}) - *Verilen isme göre bir sonuç getirilmesi*

**POST** (/add) - *Girilen değerlere göre yeni bir kayıt oluşturulması*

Endpointlere dair bazı exceptionlar yazılmıştır. (Aynı değerlerle yeni kayıt oluşturulamaması, Verilen isim yoksa sonuç dönmemesi gibi.)

***

Aşağıdaki komut ile service ayağa kaldırılmalıdır:

```
java -jar rest-api-test-0.0.1.jar
```




