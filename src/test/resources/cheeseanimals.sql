insert into cheeseanimals(cheeseId, animalId)
values ((select id from cheese where name = 'testCheese'),
        (select id from animals where name = 'testAnimal'));