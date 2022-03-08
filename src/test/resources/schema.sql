-- 创建数据库
CREATE DATABASE IF NOT EXISTS `db_order_service`;
USE `db_order_service`;

-- 创建商品类目表
CREATE TABLE `product_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类目编号',
  `category_name` varchar(64) NOT NULL COMMENT '类目名称',
  `category_type` int(11) NOT NULL COMMENT '类目类型',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `unique_category_type` (`category_type`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='类目表';

-- 创建商品信息表
CREATE TABLE `product_info` (
  `product_id` varchar(32) NOT NULL COMMENT '商品编号',
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '商品价格',
  `product_stack` int(11) NOT NULL COMMENT '库存',
  `product_description` varchar(64) NOT NULL COMMENT '描述',
  `product_icon` varchar(512) NOT NULL COMMENT '图标',
  `product_status` tinyint(3) NOT NULL COMMENT '商品状态0正常,1下架',
  `category_type` int(11) NOT NULL COMMENT '类目类型',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- 创建订单主表
CREATE TABLE `order_master` (
  `order_id` varchar(32) NOT NULL COMMENT '订单编号',
  `buyer_name` varchar(32) NOT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(64) NOT NULL COMMENT '买家微信openid',
  `order_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态，默认为新下单',
  `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态，默认未支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单主表';

-- 创建订单详情表
CREATE TABLE `order_detail` (
  `detail_id` varchar(32) NOT NULL DEFAULT '' COMMENT '详情编号',
  `order_id` varchar(32) NOT NULL COMMENT '订单编号',
  `product_id` varchar(32) NOT NULL COMMENT '商品编号',
  `product_name` varchar(64) NOT NULL COMMENT '商品名字',
  `product_price` decimal(8,2) NOT NULL COMMENT '价格,单位分',
  `product_quantity` int(11) NOT NULL COMMENT '数量',
  `product_icon` varchar(512) NOT NULL COMMENT '小图',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`detail_id`),
  KEY `idx_order_openid` (`order_id`),
  CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order_master` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情表';