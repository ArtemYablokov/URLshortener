delete from url;

-- alter sequence hibernate_sequence restart with 1;
insert into url(id, short_url_hash_id, short_url, user_url) values
(1, 1928741958, 'http://localhost:8080/Jik1UVmm', 'https://github.com/avito-tech/auto-backend-trainee-assignment')
-- (2, 1854778839, 'http://localhost:8080/9M92QQGQ', 'https://vc.ru/future/10693-modafinil'),
-- (3, 2102945762, 'http://localhost:8080/Rp0PXg5W', 'https://yandex.ru/search/?lr=213&oprnd=6506004617&text=запрос')
;