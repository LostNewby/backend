insert into
    category(name_en, name_ru, name_kz)
values
    ('Fruits', 'Фрукты', 'Жемістер'),
    ('Vegetables', 'Овощи', 'Көкөністер'),
    ('Drinks', 'Напитки', 'Сусындар'),
    ('Meats', 'Мясо', 'Ет');

insert into product(category_id, name_en, name_ru, name_kz)
select id, 'Apple', 'Яблоко', 'Алма' from category where name_en = 'Fruits'
union all select id, 'Banana', 'Банан', 'Банан' from category where name_en = 'Fruits'
union all select id, 'Lemon', 'Лимон', 'Лимон' from category where name_en = 'Fruits'
union all select id, 'Orange', 'Апельсин', 'Апельсин' from category where name_en = 'Fruits'
union all select id, 'Berries', 'Ягоды', 'Жидектер' from category where name_en = 'Fruits'
union all select id, 'Pear', 'Груша', 'Алмұрт' from category where name_en = 'Fruits'
union all select id, 'Apricot', 'Абрикос', 'Өрік' from category where name_en = 'Fruits'
union all select id, 'Peach', 'Персик', 'Шабдалы' from category where name_en = 'Fruits'
union all select id, 'Plum', 'Слива', 'Алхоры' from category where name_en = 'Fruits';


insert into product(category_id, name_en, name_ru, name_kz)
select id, 'Tomato', 'Помидор', 'Қызанақ' from category where name_en = 'Vegetables'
union all select id, 'Cucumber', 'Огурец', 'Қияр' from category where name_en = 'Vegetables'
union all select id, 'Potato', 'Картофель', 'Ақтүйнек' from category where name_en = 'Vegetables'
union all select id, 'Onion', 'Лук', 'Пияз' from category where name_en = 'Vegetables'
union all select id, 'Garlic', 'Чеснок', 'Сарымсақ' from category where name_en = 'Vegetables'
union all select id, 'Carrot', 'Морковь', 'Сәбіз' from category where name_en = 'Vegetables'
union all select id, 'Eggplant', 'Баклажан', 'Баялды' from category where name_en = 'Vegetables'
union all select id, 'Cabbage', 'Капуста', 'Орамжапырақ' from category where name_en = 'Vegetables';

insert into product(category_id, name_en, name_ru, name_kz)
select id, 'Kumis', 'Кумыс', 'Кумыс' from category where name_en = 'Drinks'
union all select id, 'Airan', 'Айран', 'Айран' from category where name_en = 'Drinks'
union all select id, 'Shubat', 'Шубат', 'Шубат' from category where name_en = 'Drinks'
union all select id, 'Milk', 'Молоко', 'Молоко' from category where name_en = 'Drinks'
union all select id, 'Saumal', 'Саумал', 'Саумал' from category where name_en = 'Drinks'
union all select id, 'Kampot', 'Кампот', 'Кампот' from category where name_en = 'Drinks';

insert into product(category_id, name_en, name_ru, name_kz)
select id, 'Horse meat', 'Конина', 'жылқы  еті' from category where name_en = 'Meats'
union all select id, 'Cow meat', 'Говядина', 'Сиыр еті' from category where name_en = 'Meats'
union all select id, 'Sheep meat', 'Баранина', 'Қой еті' from category where name_en = 'Meats'
union all select id, 'Chicken meat', 'Куриное мясо', 'Тауық еті' from category where name_en = 'Meats'
union all select id, 'Fish', 'Рыба', 'Балық' from category where name_en = 'Meats'
union all select id, 'Camel Meat', 'Верблюжье мясо', 'Түйе еті' from category where name_en = 'Meats';
