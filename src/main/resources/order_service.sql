-- 创建数据库

-- 创建商品类目表

-- 创建商品信息表

-- 新建订单主表
CREATE TABLE `order_master` (
    `order_id` VARCHAR(32) NOT NULL,
    `buyer_name` VARCHAR(32) NOT NULL COMMENT '买家名字',
    `buyer_phone` VARCHAR(32) NOT NULL COMMENT '买家电话',
    `buyer_address` VARCHAR(128) NOT NULL COMMENT '买家地址',
    `buyer_openid` VARCHAR(64) NOT NULL COMMENT '买家微信openid',
    `order_amount` DECIMAL(8 , 2 ) NOT NULL COMMENT '订单总金额',
    `order_status` TINYINT(3) NOT NULL DEFAULT '0' COMMENT '订单状态，默认为新下单',
    `pay_status` TINYINT(3) NOT NULL DEFAULT '0' COMMENT '支付状态，默认未支付',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`order_id`),
    KEY `idx_buyer_openid` (`buyer_openid`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4