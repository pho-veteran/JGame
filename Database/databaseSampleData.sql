-- Insert data into 'accounts' table
INSERT INTO accounts (username, password, accessRight) VALUES
                                                                          ('admin', 'admin123', 'Admin'),
                                                                          ('user1', 'user123', 'User'),
                                                                          ('user2', 'pass456', 'User'),
                                                                          ('user3', 'secret789', 'User'),
                                                                          ('user4', 'password123', 'User'),
                                                                          ('user5', 'qwerty', 'User'),
                                                                          ('user6', '123123', 'User');


-- Insert data into 'games' table
INSERT INTO games (gameID, price, name, description, bannerURL, genre) VALUES
                                                                           (1, 70, 'Don''t Starve Together', 'Fight, Farm, Build and Explore Together in the standalone multiplayer expansion to the uncompromising wilderness survival game.', 'src/icon/game/dont_starve.png', 'Survival'),
                                                                           (2, 140, 'Lies Of P', 'Puppet in a dark world of lies, guided in a corrupted city. Soulslike journey: adapt, battle, uncover secrets, choose truth or deception in a quest for self-discovery.', 'src/icon/game/lies_of_p.png', 'Action'),
                                                                           (3, 80, 'Stardew Valley', 'You''ve inherited your grandfather''s old farm plot in Stardew Valley. Armed with hand-me-down tools and a few coins, you set out to begin your new life. Can you learn to live off the land and turn these overgrown fields into a thriving home?', 'src/icon/game/stardew_valley.png', 'RPG'),
                                                                           (4, 120, 'Terraria', 'Dig, Fight, Explore, Build: The very world is at your fingertips as you fight for survival, fortune, and glory. Will you delve deep into cavernous expanses in search of treasure and raw materials with which to craft ever-evolving gear, machinery, and aesthetics?', 'src/icon/game/terraria.png', 'Sandbox'),
                                                                           (5, 60, 'War Thunder', 'War Thunder is the most comprehensive free-to-play, cross-platform, MMO military game dedicated to aviation, armoured vehicles, from the early 20th century to the most advanced modern combat units.', 'src/icon/game/war_thunder.jpg', 'Online Multiplayer'),
                                                                           (6, 80, 'Oxygen Not Included', 'In the space-colony simulation game Oxygen Not Included you’ll find that scarcities of oxygen, warmth and sustenance are constant threats to your colony''s survival.', 'src/icon/game/oxygen_not_included.png', 'Puzzle');

-- Insert data into 'users' table
INSERT INTO users (username, balance, primeStatus) VALUES
                                                                  ('user1', 100.0, 1),
                                                                  ('user2', 50.0, 0),
                                                                  ('user3', 75.0, 1),
                                                                  ('user4', 30.0, 0),
                                                                  ('user5', 20.0, 1),
                                                                  ('user6', 10.0, 0);

-- Insert data into 'usergames' table
INSERT INTO usergames (userID, gameID) VALUES
                                           (1, 1),
                                           (1, 2),
                                           (2, 3),
                                           (3, 4),
                                           (4, 5),
                                           (5, 6),
                                           (6, 2);
