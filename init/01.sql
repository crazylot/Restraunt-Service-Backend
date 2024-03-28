CREATE DATABASE IF NOT EXISTS `restraunt_service`;
CREATE DATABASE IF NOT EXISTS `order_service`;
CREATE DATABASE IF NOT EXISTS `auth_service`;

GRANT ALL ON `restraunt_service`.* TO 'admin'@'%';
GRANT ALL ON `order_service`.* TO 'admin'@'%';
GRANT ALL ON `auth_service`.* TO 'admin'@'%';