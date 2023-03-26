insert into product_type(product_id, name_en, name_ru, name_kz, unit)
select id, 'Gala Apple', 'Яблоко Гала', 'Гала Алмасы', 'KG' from product where name_en = 'Apple'
union all select id, 'Empire Apple', 'Яблоко Империя', 'Империя Алмасы', 'KG' from product where name_en = 'Apple'
union all select id, 'Idared Apple', 'Яблоко Айдаред', 'Айдаред Алмасы', 'KG' from product where name_en = 'Apple'
union all select id, 'Spartan Apple', 'Яблоко Спартанский', 'Спартан Алмасы', 'KG' from product where name_en = 'Apple'
union all select id, 'Banana', 'Банан', 'Банан', 'KG' from product where name_en = 'Banana'
union all select id, 'Lemon', 'Лимон', 'Лимон', 'KG' from product where name_en = 'Lemon'
union all select id, 'Orange', 'Апельсин', 'Апельсин', 'KG' from product where name_en = 'Orange'
union all select id, 'Pear', 'Груша', 'Алмұрт', 'KG' from product where name_en = 'Pear'
union all select id, 'Apricot', 'Абрикос', 'Өрік', 'KG' from product where name_en = 'Apricot'
union all select id, 'Peach', 'Персик', 'Шабдалы', 'KG' from product where name_en = 'Peach'
union all select id, 'Plum', 'Слива', 'Алхоры', 'KG' from product where name_en = 'Plum'
union all select id, 'Strawberry', 'Клубника', 'Құлпынай', 'KG' from product where name_en = 'Berries'
union all select id, 'Raspberry', 'Малина', 'Таңқурай', 'KG' from product where name_en = 'Berries'
union all select id, 'Cranberry', 'Клюква', 'Мүкжидек', 'KG' from product where name_en = 'Berries'
union all select id, 'Blackberry', 'Ежевика', 'Қаражидек', 'KG' from product where name_en = 'Berries';

insert into product_type(product_id, name_en, name_ru, name_kz, unit)
select id, 'Tomato', 'Помидор', 'Қызанақ', 'KG' from product where name_en = 'Tomato'
union all select id, 'Cucumber', 'Огурец', 'Қияр', 'KG' from product where name_en = 'Cucumber'
union all select id, 'Potato', 'Картофель', 'Ақтүйнек', 'KG' from product where name_en = 'Potato'
union all select id, 'Onion', 'Лук', 'Пияз', 'KG' from product where name_en = 'Onion'
union all select id, 'Garlic', 'Чеснок', 'Сарымсақ', 'KG' from product where name_en = 'Garlic'
union all select id, 'Carrot', 'Морковь', 'Сәбіз', 'KG' from product where name_en = 'Carrot'
union all select id, 'Eggplant', 'Баклажан', 'Баялды', 'KG' from product where name_en = 'Eggplant'
union all select id, 'Cabbage', 'Капуста', 'Орамжапырақ', 'KG' from product where name_en = 'Cabbage';

insert into product_type(product_id, name_en, name_ru, name_kz, unit)
select id, 'Kumis', 'Кумыс', 'Кумыс', 'L' from product where name_en = 'Kumis'
union all select id, 'Airan', 'Айран', 'Айран', 'L' from product where name_en = 'Airan'
union all select id, 'Shubat', 'Шубат', 'Шубат', 'L' from product where name_en = 'Shubat'
union all select id, 'Milk', 'Молоко', 'Молоко', 'L' from product where name_en = 'Milk'
union all select id, 'Saumal', 'Саумал', 'Саумал', 'L' from product where name_en = 'Saumal'
union all select id, 'Kampot', 'Кампот', 'Кампот', 'L' from product where name_en = 'Kampot';

insert into product_type(product_id, name_en, name_ru, name_kz, unit)
select id, 'Horse meat', 'Конина', 'жылқы  еті', 'KG' from product where name_en = 'Horse meat'
union all select id, 'Cow meat', 'Говядина', 'Сиыр еті', 'KG' from product where name_en = 'Cow meat'
union all select id, 'Sheep meat', 'Баранина', 'Қой еті', 'KG' from product where name_en = 'Sheep meat'
union all select id, 'Chicken meat', 'Куриное мясо', 'Тауық еті', 'KG' from product where name_en = 'Chicken meat'
union all select id, 'Fish', 'Рыба', 'Балық', 'KG' from product where name_en = 'Fish'
union all select id, 'Camel Meat', 'Верблюжье мясо', 'Түйе еті', 'KG' from product where name_en = 'Camel Meat';