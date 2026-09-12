# Car Configurator Builder

Индивидуальная работа по паттерну **Builder**. Проект показывает, как собирать автомобиль по шагам, не создавая длинные конструкторы с большим числом параметров.

## Идея проекта

`Car` — сложный неизменяемый продукт: у него есть модель, двигатель, цвет, число мест, тип трансмиссии и дополнительные опции. Один и тот же процесс настройки имеет два осмысленных представления:

- `CityCarBuilder` — городской автомобиль: минимум 4 места, двигатель до 2.0 л;
- `SportCarBuilder` — спортивное купе: 2 места, двигатель от 2.0 л.

`CarDirector` содержит повторно используемые сценарии: ежедневный городской автомобиль и спортивное купе. `Main` выступает клиентом и выводит результаты в консоль.

## Структура

```text
src/main/java/kz/sdp/carconfigurator/
├── Main.java                  # Client
├── builder/
│   ├── CarBuilder.java         # Builder interface
│   ├── CityCarBuilder.java
│   ├── SportCarBuilder.java
│   └── BaseCarBuilder.java
├── director/CarDirector.java  # Director
└── model/
    ├── Car.java               # Product
    └── Transmission.java
```

## Запуск

Нужна Java 17 или новее.

```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out kz.sdp.carconfigurator.Main
```

## Как работает Builder

Клиент выбирает подходящий builder. Director вызывает шаги в нужном порядке и возвращает готовый продукт. Если требуется своя комплектация, клиент может вызвать fluent-методы builder напрямую. Каждый такой метод возвращает тот же объект builder, поэтому настройки легко читать как цепочку.

```java
Car personalCoupe = new SportCarBuilder()
        .reset()
        .model("Orion GT")
        .engineLiters(3.0)
        .color("Graphite")
        .seats(2)
        .transmission(Transmission.AUTOMATIC)
        .build();
```

## Clean Code principles used

### 1. Meaningful names

**Before** — назначение переменных неясно:

```java
double x;
boolean a;
```

**After** — имена объясняют данные без комментария:

```java
private double engineLiters;
private boolean hasParkingSensors;
```

### 2. Small methods with one responsibility

**Before** — один метод и меняет состояние, и проверяет его, и создаёт объект:

```java
Car make() { /* set fields, validate fields, return car */ }
```

**After** — `build()` координирует только две понятные операции:

```java
public Car build() {
    validateCommonFields();
    validateBuilderRules();
    return new Car(...);
}
```

Общая проверка находится в `validateCommonFields()`, а правила конкретного представления — в `validateBuilderRules()`.

### 3. Validation close to object creation

**Before** — неверная машина могла быть создана и обнаружиться значительно позже:

```java
return new Car(model, engineLiters, seats);
```

**After** — builder прекращает сборку с точным сообщением:

```java
if (engineLiters <= 0) {
    throw new IllegalStateException("Engine volume must be positive.");
}
```

Так `Car` не бывает в некорректном состоянии.

### 4. No magic values

**Before** — непонятно, почему используется число `2.0`:

```java
if (engineLiters > 2.0) { ... }
```

**After** — константа сообщает бизнес-правило:

```java
private static final double MAX_CITY_ENGINE_LITERS = 2.0;
```

### 5. Immutable product

**Before** — после создания автомобиля его поля можно было бы случайно поменять:

```java
car.setColor("Red");
```

**After** — все поля `Car` имеют `final`, сеттеров нет:

```java
private final String color;
```

Изменения происходят только во время понятного этапа сборки, а готовый результат безопасно передавать другим частям программы.

### 6. Focused classes and separation of responsibilities

**Before** — `Main` мог бы одновременно хранить правила комплектаций, собирать автомобиль и выводить его.

**After** — обязанности разделены: `Car` хранит результат, builders знают правила своей комплектации, `CarDirector` хранит готовые рецепты, а `Main` только демонстрирует сценарий. Это упрощает тестирование и дальнейшее добавление нового типа автомобиля.

## Ответы для защиты

**Почему Builder, а не конструктор?** У автомобиля много необязательных параметров. Builder избавляет от конструктора с длинным списком аргументов и делает конфигурацию читаемой.

**Зачем Director?** Он сохраняет готовые рецепты. Можно повторно создать одинаковую комплектацию без копирования цепочки вызовов в клиентский код.

**Чем отличаются Builder и Director?** Builder знает, как по шагам создать конкретный продукт; Director знает порядок шагов для типовой конфигурации.

**Что даёт fluent API?** Каждый метод настройки возвращает `this`, поэтому вызовы можно соединить в одну понятную цепочку.

## Suggested incremental commits

Чтобы выполнить требование задания об истории, сделайте как минимум три осмысленных коммита:

1. `feat: add immutable car product and builder interface`
2. `feat: add city and sport builders with director recipes`
3. `docs: add clean code explanation and usage guide`

Код написан как самостоятельная реализация для учебного задания. Перед сдачей проверьте, что вы понимаете каждое решение и можете объяснить его на защите.

