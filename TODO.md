- [x] надо ли запускать на WildFly?





В WildFly
~~- [ ] создание экземпляра серверов через domain mode~~
- [x] создание экземпляра серверов через standalone mode
- [x] настройка ejb в subsystem профиля
- [ ] keycloak
- [x] haproxy
- [x] надо как-то найти адрес 1 сервиса из консула

Поправить:
- [ ] нельзя удалить, если евент начался и не закончился. Но можно удалить, если закончилось!
- [ ] ошибки 422 не бывает для url значений

Добавить ошибку для вырубления EJB сервиса:
{
"detail": "jakarta.ejb.NoSuchEJBException: EJBCLIENT000079: Unable to discover destination for request for EJB StatelessEJBLocator for \"/business-module-1.0-SNAPSHOT/BusinessService\", view is interface org.example.businessmodule.BusinessServiceRemote, affinity is None",
"instance": "http://localhost:8090/bookingService/TMA/api/v2/booking",
"title": "Внутренняя ошибка сервера"
}