insert into answerstoquestions values ('1', '1'),
                                      ('1', '2'),
                                      ('2', '3'),
                                      ('2', '4'),
                                      ('3', '5'),
                                      ('4', '6'),
                                      ('5', '7'),
                                      ('5', '8'),
                                      ('6', '9'),
                                      ('6', '10')
                                      on conflict do nothing;