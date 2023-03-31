insert into cheese(name, vegetarian, countryId, colourId, likes, dislikes, version)
values ('testCheese', false,
        (select id from countries where name = 'testCountry'),
        (select id from colours where name = 'testColour'), 1, 1, 0),
       ('testCheese2', false,
        (select id from countries where name = 'testCountry'),
        (select id from colours where name = 'testColour'), 1, 1, 0);