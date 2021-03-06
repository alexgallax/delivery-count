# Delivery Count

Вычисление стоимости доставки.

## Функция вычисления стоимости доставки

### Описание

Сигнатура функции:

```
double countPrice(
    int destinationKm,
    boolean bulky,
    boolean fragile,
    DeliveryLoadLevel deliveryLoadLevel
)
```

Входные аргументы:

* `destinationKm` - расстояние (километры)
* `bulky` - габариты (true - большие, false - малые)
* `fragile` - хрупкость (true - хрупкий, false - нет)
* `deliveryLoadLevel` - уровень загруженности службы доставки (очень высокий, высокий, повышенный, стандартный)

Функция возвращает рассчитанную стоимость. Если рассчитанная стоимость меньше установленного минимального значения, возвращается минимальная стоимость (400 рублей).

В случае невозможности расчета стоимости функция должна выбросить Error с соответствующим сообщением:

* Нельзя перевозить хрупкие грузы на расстояние больше 30 километров.
* Расстояние меньше или равно 0.

### Пример использования

```
createDeliverCalculator().countPrice(10, false, true, DeliveryLoadLevel.STANDARD);
```

Расчет стоимости доставки малогабаритного хрупкого груза на расстояние 10 километров при стандартном уровне загрузки службы доставки.

## Автотесты

### Описание

Для запуска автотестов используется `Gradle`. После прохождения автотестов можно сформировать `Allure` отчет.

Структура проекта автотестов:

* `consts` - константы, используемые в автотестах
* `deliverycounttests` - код автотестов
* `matchers` - кастомные матчеры для автотестов
* `resources` - csv-файлы с тестовыми данными для параметризованных автотестов

Параметры запуска автотестов:

* `parallel` - количество параллельно запускаемых автотестов
* `name` - `{ClassName}`: запуск всех тестов класса, `{ClassName}.{MethodName}`: запуск указанного теста

### Запуск автотестов

```
./gradlew clean test [args]
```

### Пример запука автотестов

Запуск всех автотестов:

```
./gradlew clean test
```

Запуск всех автотестов в 4 параллельных потока:

```
./gradlew clean test -Dparallel=4
```

Запуск всех автотестов в классе с именем `ValidInputDeliveryPrice`:

```
./gradlew clean test -Dname=ValidInputDeliveryPrice
```

Запуск автотеста с именем метода `shouldHaveCorrectPriceIfValidInput` в классе с именем `ValidInputDeliveryPrice`:

```
./gradlew clean test -Dname=ValidInputDeliveryPrice.shouldHaveCorrectPriceIfValidInput
```

### Построение отчета

```
./gradlew allureServe
```

## Ответы на вопросы

[Описание функции расчета стоимости доставки](./docs/delivery-price-function.md)

[Описание автотестов](./docs/test-coverage.md)

[Ответы на вопросы о Яндекс.Практикуме](./docs/practicum.md)
