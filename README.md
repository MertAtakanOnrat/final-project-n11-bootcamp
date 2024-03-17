Swagger url: http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/
http://localhost:8081/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/
http://localhost:8082/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/

restaurant veriler db de değil solr da tutuluyor!

average rate hesabı için restaurant tarafından user_reviewlere erişebilmek lazım
bu şekilde ortalama alıp hesaplanmalı.

yeni bir user-review eklenince silinice ve güncellenince asenkron bir 
şekilde average hesaplanmalı

docker ile tüm uygulamaları ayağa kaldır

solr kullanarak optimum bir sorgu çalıştır 

önce bunu:
{
"add-field-type": {
"name": "location_restaurant",
"class": "solr.LatLonPointSpatialField",
"docValues": "true"
}
}

sonra bunu çalıştırdık:
{
"add-field": {
"name": "location",
"type": "location_restaurant",
"indexed": true,
"stored": true
}
}
bunları unutma


average rate'i null olanlarda hata fırlıyor onu düzelt

postgre sql bilgilerini application properties kısmında gir user-service ve logger-service için 

