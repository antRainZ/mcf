package org.antrain.framework.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.antrain.common.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;


public class CreateAndUpdateMetaObjectHandler implements MetaObjectHandler {
	@Override
	public void insertFill(MetaObject metaObject) {
		//region 处理创建人信息
		Object createBy = this.getFieldValByName("createBy", metaObject);
		Object createTime = this.getFieldValByName("createTime", metaObject);
		if (createBy == null) {
			try {
				createBy = SecurityUtils.getUsername();
				this.setFieldValByName("createBy", createBy, metaObject);
			} catch (Exception e) {

			}
		}
		if (createTime == null) {
			createTime = new Date();
			this.setFieldValByName("createTime", createTime, metaObject);
		}
		//endregion
		//region 处理修改人信息
		Object updateBy = this.getFieldValByName("updateBy", metaObject);
		Object updateTime = this.getFieldValByName("updateTime", metaObject);
		if (updateBy == null) {
			updateBy = createBy;
			this.setFieldValByName("updateBy", updateBy, metaObject);
		}
		if (updateTime == null) {
			updateTime = createTime;
			this.setFieldValByName("updateTime", updateTime, metaObject);
		}
		//endregion
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		//region 处理修改人信息
		Object updateBy = this.getFieldValByName("updateBy", metaObject);
		Object updateTime = this.getFieldValByName("updateTime", metaObject);
		if (updateBy == null) {
			try {
				updateBy = SecurityUtils.getUsername();
				this.setFieldValByName("createBy", updateBy, metaObject);
			} catch (Exception e) {

			}
		}
		if (updateTime == null) {
			updateTime = new Date();
			this.setFieldValByName("updateTime", updateTime, metaObject);
		}
		//endregion
	}

	@Override
	public boolean openInsertFill() {
		return true;
	}

	@Override
	public boolean openUpdateFill() {
		return true;
	}
}
