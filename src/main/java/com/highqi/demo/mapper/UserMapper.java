package com.highqi.demo.mapper;

import com.highqi.demo.doman.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface UserMapper {

    /**
     * 保存用户.
     * @param user 用户对象
     * @return 成功 1
     */
    @Insert("insert into user (userid, headimgurl, openid, \n" +
            "      sex, nickname, phone, \n" +
            "      hobby, forumheadimgurl)\n" +
            "    values (#{userid,jdbcType=VARCHAR}, #{headimgurl,jdbcType=VARCHAR}, #{openid,jdbcType=VARCHAR}, \n" +
            "      #{sex,jdbcType=VARCHAR}, #{nickname,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, \n" +
            "      #{hobby,jdbcType=VARCHAR}, #{forumheadimgurl,jdbcType=VARCHAR})")
    int insert(User user);

    /**
     * 根据用户id查询该用户.
     * @param userid 用户id
     * @return  User
     */
    @Select("select userid, headimgurl, openid, sex, nickname, phone, hobby, forumheadimgurl\n" +
            "    from user\n" +
            "    where userid = #{userid,jdbcType=VARCHAR}")
    User selectByPrimaryKey(String userid);

    /**
     * 根据openid查询该用户.
     * @param openid 用户微信的openid
     * @return  User
     */
    @Select("select userid, headimgurl, openid, sex, nickname, phone, hobby, forumheadimgurl\n" +
            "    from user\n" +
            "    where openid = #{openid,jdbcType=VARCHAR}")
    User selectByOpenid(String openid);

    /**
     * 跟新用户.
     * @param record 要更新的用户
     * @return  成功跟新的条数
     */
    @Update("update user\n" +
            "    set headimgurl = #{headimgurl,jdbcType=VARCHAR},\n" +
            "      openid = #{openid,jdbcType=VARCHAR},\n" +
            "      sex = #{sex,jdbcType=VARCHAR},\n" +
            "      nickname = #{nickname,jdbcType=VARCHAR},\n" +
            "      phone = #{phone,jdbcType=VARCHAR},\n" +
            "      hobby = #{hobby,jdbcType=VARCHAR}" +
            " where userid = #{userid,jdbcType=VARCHAR}")
   Integer updateByPrimaryKey(User record);

    /**
     * 跟新用户论坛头像.
     * @param userid 要更新的用户id
     * @return  成功跟新的条数
     */
    @Update("update user\n" +
            "    set forumheadimgurl = #{forumheadimgurl,jdbcType=VARCHAR}\n" +
            "    where userid = #{userid,jdbcType=VARCHAR}")
    Integer updateForumheadimgurl(@Param("forumheadimgurl") String forumheadimgurl , @Param("userid") String userid);

}