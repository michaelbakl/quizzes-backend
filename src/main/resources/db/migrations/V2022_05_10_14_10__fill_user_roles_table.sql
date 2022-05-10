insert into userroles values ('user', 'USER'),
                             ('admin', 'USER'),
                             ('admin', 'ADMIN') on conflict do nothing;