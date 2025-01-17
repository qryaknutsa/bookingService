- [x] надо ли запускать на WildFly?





В WildFly
~~- [ ] создание экземпляра серверов через domain mode~~
- [x] создание экземпляра серверов через standalone mode
- [x] настройка ejb в subsystem профиля
- [x] keycloak
- [x] haproxy
- [x] надо как-то найти адрес 1 сервиса из консула

Поправить:
- [ ] нельзя удалить, если евент начался и не закончился. Но можно удалить, если закончилось!
- [ ] ошибки 422 не бывает для url значений 
- [ ] Добавить ошибку для вырубления EJB сервиса -- 503 status code
  {
  "detail": "org.jboss.ejb.client.RequestSendFailedException: EJBCLIENT000409: No more destinations are available",
  "instance": "http://localhost:8090/bookingService/TMA/api/v2/booking/event/57",
  "title": "Внутренняя ошибка сервера"
  }
- [x] понять jndiProperties, зачем нужны, что значит  WFNAM00051: Provider URLs already given via standard mechanism; ignoring legacy property-based connection configuration в логах сервера


- [ ] Опять проблемы с отображением дат(