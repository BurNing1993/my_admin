INSERT INTO  `user`(`id`, `create_time`, `update_time`, `created_by`, `updated_by`, `cellphone`, `email`, `has_deleted`, `nickname`, `password`, `username`) VALUES (1, NULL, NULL, NULL, NULL, '19912345678', '123@qq.com', 0, 'Joey', 'd83b6339-7f85-4521-b50f-db8fb7ae0d46', 'admin');

INSERT INTO  `role`(`id`, `create_time`, `update_time`, `created_by`, `updated_by`, `has_deleted`, `role_name`) VALUES (1, NULL, NULL, NULL, NULL, 0, 'ROLE_USER');
INSERT INTO  `role`(`id`, `create_time`, `update_time`, `created_by`, `updated_by`, `has_deleted`, `role_name`) VALUES (2, NULL, NULL, NULL, NULL, 0, 'ROLE_ADMIN');

INSERT INTO  `user_role`(`id`, `create_time`, `update_time`, `created_by`, `updated_by`, `role_id`, `user_id`) VALUES (1, NULL, NULL, NULL, NULL, 1, 1);