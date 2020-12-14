package jp.co.internous.grapes.model.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.grapes.model.domain.MstUser;

@Mapper
public interface MstUserMapper {
	//@Insert INSERT INTO　テーブル名でテーブルに新しいデータを追加させる（SQL文入力）
	@Insert("INSERT INTO mst_user ("
			+ "user_name, password, "
			+ "family_name, first_name, family_name_kana, first_name_kana, "
			+ "gender"
			+ ") "
			+ "VALUES ("
			+ "#{userName}, #{password}, "
			+ "#{familyName}, #{firstName}, #{familyNameKana}, #{firstNameKana}, "
			+ "#{gender}"
			+ ")")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int insert(MstUser user);
	
	//ユーザー名とパスワードを引数に受け取り、userのリストを返却するメソッド宣言
	@Select("SELECT * FROM mst_user WHERE user_name = #{userName} AND password = #{password}")
	MstUser findByUserNameAndPassword(
			@Param("userName") String userName,
			@Param("password") String password);

	
	 // ログインするため、ユーザーネームとパスワードと同じものを検索する")
	@Select("SELECT count(id) FROM mst_user WHERE user_name = #{userName}")
	int findCountByUserName(@Param("userName") String userName);
	
	// パスワード新更新

	@Update("UPDATE mst_user SET password = #{password}, updated_at = now() WHERE user_name = #{userName}")
	int updatePassword(
			@Param("userName") String userName,
			@Param("password") String password);
	

}
