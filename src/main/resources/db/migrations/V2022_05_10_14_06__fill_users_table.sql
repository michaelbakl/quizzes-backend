insert into users values ('user', '$2a$12$UWtdPoXWalzaxxA4NzqPau6gqxvU.YKZtDV0rMzXc/2d0BqGqN9r2', true),
                         ('admin', '$2a$12$6PeOIvwahyBiSp64vuGoqO4hfbRUG41/bcz.XJzc7zNVogIucsGY6', true)
                  on conflict do nothing;