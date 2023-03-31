insert into webpages(cheeseId, url)
values ((select id from cheese where name = 'testCheese'), 'test.com'),
       ((select id from cheese where name = 'testCheese'), 'test2.com');