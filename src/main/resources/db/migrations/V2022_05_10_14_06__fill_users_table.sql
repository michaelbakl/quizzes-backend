insert into users values
('7df2eb5c-d119-11ec-9d64-0242ac120002', 'user@example.com',
 '$2a$12$UWtdPoXWalzaxxA4NzqPau6gqxvU.YKZtDV0rMzXc/2d0BqGqN9r2', true),
('7df2ed46-d119-11ec-9d64-0242ac120002', 'admin@example.com',
 '$2a$12$6PeOIvwahyBiSp64vuGoqO4hfbRUG41/bcz.XJzc7zNVogIucsGY6', true)
                  on conflict do nothing;