package cn.edu.xmu.javaee.productdemoaop.mapper.link;

import cn.edu.xmu.javaee.productdemoaop.mapper.generator.ProductPoSqlProvider;
import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.OnSalePo;
import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.ProductPo;
import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.ProductPoExample;
import cn.edu.xmu.javaee.productdemoaop.mapper.manual.po.ProductAllPo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
@Mapper
public interface ProductAllMapper_link {

        @Select({
                "select",
                "p.id, p.sku_sn, p.name, p.original_price, p.weight, p.barcode, p.unit, ",
                "p.origin_place, p.commission_ratio, p.free_threshold, p.status, p.creator_id, p.creator_name, ",
                "p.modifier_id, p.modifier_name, p.gmt_create, p.gmt_modified, ",
                "o.id as s_id, o.product_id as s_product_id,o.price as s_price, o.begin_time as s_begin_time, o.end_time as s_end_time, o.quantity as s_quantity, o.max_quantity as s_max_quantity, ",
                "o.creator_id as s_creator_id, o.creator_name as s_creator_name, ",
                "o.modifier_id as s_modifier_id, o.modifier_name as s_modifier_name, ",
                "o.gmt_create as s_gmt_create, o.gmt_modified as s_gmt_modified, ",
                "op.id as o_id, op.sku_sn as o_sku_sn, op.name as o_name, op.original_price as o_original_price, op.weight as o_weight, op.barcode as o_barcode, ",
                "op.unit as o_unit, op.origin_place as o_origin_place, op.creator_id as o_creator_id, ",
                "op.creator_name as o_creator_name, op.modifier_id as o_modifier_id, ",
                "op.modifier_name as o_modifier_name, op.gmt_create as o_gmt_create, ",
                "op.gmt_modified as o_gmt_modified ",
                "from goods_product p ",
                "left join goods_onsale o on p.id = o.product_id ",
                "left join goods_product op on p.goods_id = op.goods_id ",
                "where o.begin_time <= NOW() and o.end_time > NOW() and p.name = #{name}"

        })
        @Results({
                // Product columns mapping
                @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
                @Result(column="sku_sn", property="skuSn", jdbcType=JdbcType.VARCHAR),
                @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
                @Result(column="original_price", property="originalPrice", jdbcType=JdbcType.BIGINT),
                @Result(column="weight", property="weight", jdbcType=JdbcType.BIGINT),
                @Result(column="barcode", property="barcode", jdbcType=JdbcType.VARCHAR),
                @Result(column="unit", property="unit", jdbcType=JdbcType.VARCHAR),
                @Result(column="origin_place", property="originPlace", jdbcType=JdbcType.VARCHAR),
                @Result(column="commission_ratio", property="commissionRatio", jdbcType=JdbcType.INTEGER),
                @Result(column="free_threshold", property="freeThreshold", jdbcType=JdbcType.BIGINT),
                @Result(column="status", property="status", jdbcType=JdbcType.SMALLINT),
                @Result(column="creator_id", property="creatorId", jdbcType=JdbcType.BIGINT),
                @Result(column="creator_name", property="creatorName", jdbcType=JdbcType.VARCHAR),
                @Result(column="modifier_id", property="modifierId", jdbcType=JdbcType.BIGINT),
                @Result(column="modifier_name", property="modifierName", jdbcType=JdbcType.VARCHAR),
                @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
                @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP),
                @Result(property = "onSaleList", many = @Many(resultMap = "SaleMap", columnPrefix = "s_")),
                @Result(property = "otherProduct", many = @Many(resultMap = "ProductMap", columnPrefix = "o_"))
        })
        List<ProductAllPo> getProductByName_link(String name);

        @Select("SELECT id FROM goods_onsale WHERE id = #{id}")
        @Results(id = "SaleMap", value = {
                @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
                @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
                @Result(column="price", property="price", jdbcType=JdbcType.BIGINT),
                @Result(column="begin_time", property="beginTime", jdbcType=JdbcType.TIMESTAMP),
                @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP),
                @Result(column="quantity", property="quantity", jdbcType=JdbcType.INTEGER),
                @Result(column="max_quantity", property="maxQuantity", jdbcType=JdbcType.INTEGER),
                @Result(column="creator_id", property="creatorId", jdbcType=JdbcType.BIGINT),
                @Result(column="creator_name", property="creatorName", jdbcType=JdbcType.VARCHAR),
                @Result(column="modifier_id", property="modifierId", jdbcType=JdbcType.BIGINT),
                @Result(column="modifier_name", property="modifierName", jdbcType=JdbcType.VARCHAR),
                @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
                @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP)
        })
        List<OnSalePo> selectLastOnSaleByProductId(Long productId);
        @Select("SELECT id FROM goods_product WHERE id = #{id}")
        @Results(id = "ProductMap", value = {
                @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
                @Result(column="sku_sn", property="skuSn", jdbcType=JdbcType.VARCHAR),
                @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
                @Result(column="original_price", property="originalPrice", jdbcType=JdbcType.BIGINT),
                @Result(column="weight", property="weight", jdbcType=JdbcType.BIGINT),
                @Result(column="barcode", property="barcode", jdbcType=JdbcType.VARCHAR),
                @Result(column="unit", property="unit", jdbcType=JdbcType.VARCHAR),
                @Result(column="origin_place", property="originPlace", jdbcType=JdbcType.VARCHAR),
                @Result(column="creator_id", property="creatorId", jdbcType=JdbcType.BIGINT),
                @Result(column="creator_name", property="creatorName", jdbcType=JdbcType.VARCHAR),
                @Result(column="modifier_id", property="modifierId", jdbcType=JdbcType.BIGINT),
                @Result(column="modifier_name", property="modifierName", jdbcType=JdbcType.VARCHAR),
                @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
                @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP)
        })
        ProductPo selectOtherProduct(Long goodsId);

}
