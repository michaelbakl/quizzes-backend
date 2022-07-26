insert into users values
('7df2eb5c-d119-11ec-9d64-0242ac120002', 'user@example.com',
 '$2a$12$0RVBgU/tVIsbOBW5VFOCwurhp/wEIZ05lMUbp/jSApz0zpJulqYOa', true),
('7df2ed46-d119-11ec-9d64-0242ac120002', 'admin@example.com',
 '$2a$12$hwbFyQIB/6NbeAMPmG0Kg.MRbMl0AGu3ehd7bU2uL23vVRzOLAtzW', true)
                  on conflict do nothing;