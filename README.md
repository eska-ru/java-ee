### Занятие 1

1. Установить среду разработки IntelliJ IDEA (если не установлена)

2. Установить сервер приложений WIldfly.

3. Создать и запустить новый проект по инструкции из данной методички

4. Создать в приложении одни класс сервлета по инструкции преподавателя

5. Добавить логирование в класс сервлета

6. В качестве ДЗ помимо кода выложить фрагмент лога сервера приложений со следами работы вашего приложения

### Занятие 2

1. Сервлеты будут выполнять роль контроллеров в разрабатываемом приложении, поэтому необходимо создать:
    1. Сервлет для главной страницы /main;
    2. Сервлет для каталога товаров /catalog;
    3. Сервлет для товара /product;
    4. Сервлет для корзины /cart;
    5. Сервлет для оформления заказа /order.
2. В каждый сервлет добавить:
    1. Заголовок с именем страницы (товары, товар, корзина и т.д.);
    2. Список (навигационное меню), с помощью которого можно перемещаться между созданными сервлетами.
3. Добавить обработку исключений для кодов 404 и 403.
4. Создать фильтр для всех сервлетов, который будет добавлять к заголовку HTTP-ответа кодировку UTF-8.

### Занятие 3

1. Создать jsp-страницы и сервлеты-контроллеры для сущностей Пользователь (User) и Категория продукта 
   (Category). Можно для какой-то одной, но лучше создать обе.

2. Реализовать для этих сущностей весь функционал, показанный преподавателем на уроке для сущности Product.

3. Добавить к этому функционалу возможность создавать новый экземпляр сущности и удалять сущность.

4. Можно и нужно использовать те же HTML шаблоны, что на уроке использовал преподаватель.

### Занятие 4

1. Переписать ранее написанное приложение при помощи технологий JSF и CDI.
2. Реализовать корзину при помощи сессионного бина.

### Занятие 5

1. Создать сущности для:
   1. Товаров;
   2. Категорий;
   3. Заказов.
2. Создать DAO классы для:
   1. Добавления, обновления, удаления, выборки всех товаров, выборки товара по идентификатору;
   2. Добавления, обновления, удаления, выборки всех категорий, выборки категории по идентификатору;
   3. Добавления, обновления, удаления, выборки всех заказов, выборки заказа по идентификатору.
3. Создать именованные запросы, с помощью JPQL.
4. Провести связь один ко многим между продуктами и категориями.

### Занятие 6

1. Создать сервисный слой, который будет взаимодействовать с контроллером и вызывать DAO-методы модели. Сервисный 
   слой должен обращаться к DAO-методам, которые осуществляют выборку из БД.
2. Создать statless-бины для каталога товаров, для выборки товаров, для одного товара.
3. Создать statfull-бин для реализации корзины.