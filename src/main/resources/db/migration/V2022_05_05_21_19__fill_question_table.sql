insert into question values ('1', 'question1', '1'),
                            ('2', 'question1', '3'),
                            ('3', 'question1', '5'),
                            ('4', 'question1', '6'),
                            ('5', 'question1', '7'),
                            ('6', 'question1', '9')
                     on conflict do nothing;