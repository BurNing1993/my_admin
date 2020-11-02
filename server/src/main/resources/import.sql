INSERT INTO `my_admin`.`sys_user`(`id`, `create_time`, `update_time`, `created_by`, `updated_by`, `cellphone`, `email`, `enabled`, `has_deleted`, `nickname`, `password`, `username`) VALUES (1, '2020-10-19 03:33:24.183363', '2020-10-19 03:33:24.211321', 'anonymousUser', 'anonymousUser', NULL, NULL, 1, 0, 'joey1234', '$2a$10$A0qc2on9ZwZmvzTVf3IDSOwe2BnQ7y6RuT6R27qfS8UxknO0IOL0i', 'joey1234');

INSERT INTO `my_admin`.`sys_role`(`id`, `create_time`, `update_time`, `created_by`, `updated_by`, `has_deleted`, `role_name`, `role_desc`) VALUES (1, NULL, NULL, NULL, NULL, 0, 'ADMIN', '管理员');
INSERT INTO `my_admin`.`sys_role`(`id`, `create_time`, `update_time`, `created_by`, `updated_by`, `has_deleted`, `role_name`, `role_desc`) VALUES (2, NULL, NULL, NULL, NULL, 0, 'USER', '普通用户');


INSERT INTO `my_admin`.`sys_user_role`(`user_id`, `role_id`) VALUES (1, 1);
