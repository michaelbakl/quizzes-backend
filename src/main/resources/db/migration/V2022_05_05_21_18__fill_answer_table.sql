insert into answer values ('1', 'answer1', 1),
                          ('2', 'answer2', 0),
                          ('3', 'answer3', 1),
                          ('4', 'answer4', 0),
                          ('5', 'answer5', 1),
                          ('6', 'answer6', 0),
                          ('7', 'answer7', 1),
                          ('8', 'answer8', 0),
                          ('9', 'answer9', 1),
                          ('10', 'answer10', 1)
                          on conflict do nothing;
