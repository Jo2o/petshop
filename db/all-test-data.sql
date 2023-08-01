INSERT INTO public.credential ("password",roles) VALUES ('$2a$10$45K2g46wQVYS3Iuio1/IquBYGsMw1ziZD0Xc8GF/UaLWDOm9Eu2d6','USER');
INSERT INTO public.credential ("password",roles) VALUES ('$2a$10$PJcirRnB.mnJYkIu8Y7bzOK7kRIgy.eveNeMR6Kl3491JiCWgSodO','USER');
INSERT INTO public.credential ("password",roles) VALUES ('$2a$10$CgUCcsSGEYXNIl8kiCYgAelJ6k4wmP7adye7xssQdnmLvpnY18oxG','USER');
INSERT INTO public.credential ("password",roles) VALUES ('$2a$10$xv.LCOpW0hWT04LVXUEmoe9jm8Q2ba5x4Le3QUXj1LMbJn1S5Bwl2','USER');

INSERT INTO public."user" (username,email,credential_id) VALUES ('karol','karol',2);
INSERT INTO public."user" (username,email,credential_id) VALUES ('jana','jana',3);
INSERT INTO public."user" (username,email,credential_id) VALUES ('zuza','zuza',4);
INSERT INTO public."user" (username,email,credential_id) VALUES ('juro','juro',5);

INSERT INTO public."order" (price,created,user_id) VALUES (19998,'2020-11-09 23:40:06',5);
INSERT INTO public."order" (price,created,user_id) VALUES (8844,'2020-11-09 23:43:42',6);
INSERT INTO public."order" (price,created,user_id) VALUES (62216,'2020-11-11 00:20:00',7);
INSERT INTO public."order" (price,created,user_id) VALUES (4444,'2020-11-11 00:21:28',8);

INSERT INTO public.product (name,categories,price,description,image_urls) VALUES ('11','DOGS,OTHER',1111,'11','11');
INSERT INTO public.product (name,categories,price,description,image_urls) VALUES ('22','DOGS,OTHER',2222,'22','22');
INSERT INTO public.product (name,categories,price,description,image_urls) VALUES ('33','DOGS',3333,'33','33');
INSERT INTO public.product (name,categories,price,description,image_urls) VALUES ('44','CATS',4400,'44','44');
INSERT INTO public.product (name,categories,price,description,image_urls) VALUES ('55','OTHER',5555,'55','55');
INSERT INTO public.product (name,categories,price,description,image_urls) VALUES ('66','CATS,DOGS,OTHER',6666,'66','66');
INSERT INTO public.product (name,categories,price,description,image_urls) VALUES ('77','CATS,DOGS,OTHER',7777,'77','77');

INSERT INTO public.ordered_item (count,price,product_id,order_id) VALUES (6,2222,2,17);
INSERT INTO public.ordered_item (count,price,product_id,order_id) VALUES (2,3333,3,17);
INSERT INTO public.ordered_item (count,price,product_id,order_id) VALUES (4,1111,1,18);
INSERT INTO public.ordered_item (count,price,product_id,order_id) VALUES (1,4400,4,18);
INSERT INTO public.ordered_item (count,price,product_id,order_id) VALUES (7,5555,5,19);
INSERT INTO public.ordered_item (count,price,product_id,order_id) VALUES (7,3333,3,19);
INSERT INTO public.ordered_item (count,price,product_id,order_id) VALUES (1,1111,1,20);
INSERT INTO public.ordered_item (count,price,product_id,order_id) VALUES (1,3333,3,20);

