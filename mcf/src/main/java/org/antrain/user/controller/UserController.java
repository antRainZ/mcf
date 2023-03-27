package org.antrain.user.controller;

import java.util.List;
import java.util.Arrays;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import org.antrain.common.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.antrain.common.annotation.Log;
import org.antrain.common.core.controller.BaseController;
import org.antrain.common.core.domain.AjaxResult;
import org.antrain.common.enums.BusinessType;
import org.antrain.user.vo.UserVo;
import org.antrain.user.bo.UserQueryBo;
import org.antrain.user.bo.UserAddBo;
import org.antrain.user.bo.UserEditBo;
import org.antrain.user.service.IUserService;
import org.antrain.common.utils.poi.ExcelUtil;
import org.antrain.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户Controller
 * 
 * @author antrain
 * @date 2021-04-11
 */
@Api(value = "用户控制器", tags = {"用户管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/user/user")
public class UserController extends BaseController {

    private final IUserService iUserService;

    /**
     * 查询用户列表
     */
    @ApiOperation("查询用户列表")
    @PreAuthorize("@ss.hasPermi('user:user:list')")
    @GetMapping("/list")
    public TableDataInfo<UserVo> list(UserQueryBo bo) {
        startPage();
        List<UserVo> list = iUserService.queryList(bo);
        return getDataTable(list);
    }

    /**
     * 导出用户列表
     */
    @ApiOperation("导出用户列表")
    @PreAuthorize("@ss.hasPermi('user:user:export')")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<UserVo> export(UserQueryBo bo) {
        List<UserVo> list = iUserService.queryList(bo);
        ExcelUtil<UserVo> util = new ExcelUtil<UserVo>(UserVo.class);
        return util.exportExcel(list, "user" );
    }

    /**
     * 获取用户详细信息
     */
    @ApiOperation("获取用户详细信息")
    @PreAuthorize("@ss.hasPermi('user:user:query')")
    @GetMapping("/{id}")
    public AjaxResult<UserVo> getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iUserService.queryById(id));
    }

    /**
     * 新增用户
     */
    @ApiOperation("新增用户")
    @PreAuthorize("@ss.hasPermi('user:user:add')")
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult<Void> add(@RequestBody UserAddBo bo) {
        //System.out.println(bo);
        bo.setPassword(DigestUtil.sha256Hex(bo.getPassword()));
        return toAjax(iUserService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改用户
     */
    @ApiOperation("修改用户")
    @PreAuthorize("@ss.hasPermi('user:user:edit')")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult<Void> edit(@RequestBody UserEditBo bo) {
        if (StrUtil.isNotBlank(bo.getPassword())) {
            bo.setPassword(DigestUtil.sha256Hex(bo.getPassword()));
        }
        return toAjax(iUserService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除用户
     */
    @ApiOperation("删除用户")
    @PreAuthorize("@ss.hasPermi('user:user:remove')")
    @Log(title = "用户" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iUserService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
