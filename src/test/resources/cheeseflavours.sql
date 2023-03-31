insert into cheeseflavours(cheeseId, flavourId)
values ((select id from cheese where name = 'testCheese'), (select id from flavours where name = 'testFlavour'));