package org.antrain.user.controller;

import java.util.List;
import java.util.Arrays;

import lombok.RequiredArgsConstructor;
import org.antrain.user.domain.User;
import org.antrain.user.service.IUserService;
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
import org.antrain.user.vo.IntegralVo;
import org.antrain.user.bo.IntegralQueryBo;
import org.antrain.user.bo.IntegralAddBo;
import org.antrain.user.bo.IntegralEditBo;
import org.antrain.user.service.IIntegralService;
import org.antrain.common.utils.poi.ExcelUtil;
import org.antrain.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户积分记录Controller
 * 
 * @author antrain
 * @date 2021-04-11
 */
@Api(value = "用户积分记录控制器", tags = {"用户积分记录管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/user/integral")
public class IntegralController extends BaseController {

    private final IUserService iUserService;
    private final IIntegralService iIntegralService;

    /**
     * 查询用户积分记录列表
     */
    @ApiOperation("查询用户积分记录列表")
    @PreAuthorize("@ss.hasPermi('user:integral:list')")
    @GetMapping("/list")
    public TableDataInfo<IntegralVo> list(IntegralQueryBo bo) {
        startPage();
        List<IntegralVo> list = iIntegralService.queryList(bo);
        return getDataTable(list);
    }

    /**
     * 导出用户积分记录列表
     */
    @ApiOperation("导出用户积分记录列表")
    @PreAuthorize("@ss.hasPermi('user:integral:export')")
    @Log(title = "用户积分记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<IntegralVo> export(IntegralQueryBo bo) {
        List<IntegralVo> list = iIntegralService.queryList(bo);
        ExcelUtil<IntegralVo> util = new ExcelUtil<IntegralVo>(IntegralVo.class);
        return util.exportExcel(list, "integral" );
    }

    /**
     * 获取用户积分记录详细信息
     */
    @ApiOperation("获取用户积分记录详细信息")
    @PreAuthorize("@ss.hasPermi('user:integral:query')")
    @GetMapping("/{id}")
    public AjaxResult<IntegralVo> getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iIntegralService.queryById(id));
    }

    /**
     * 新增用户积分记录
     */
    @ApiOperation("新增用户积分记录")
    @PreAuthorize("@ss.hasPermi('user:integral:add')")
    @Log(title = "用户积分记录", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult<Void> add(@RequestBody IntegralAddBo bo) {
        User user = new User();
        user.setId(bo.getUserId());
        user.setIntegral(bo.getAddValue()+bo.getPerValue());
        iUserService.updateById(user);
        return toAjax(iIntegralService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改用户积分记录
     */
    @ApiOperation("修改用户积分记录")
    @PreAuthorize("@ss.hasPermi('user:integral:edit')")
    @Log(title = "用户积分记录", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult<Void> edit(@RequestBody IntegralEditBo bo) {
        return toAjax(iIntegralService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除用户积分记录
     */
    @ApiOperation("删除用户积分记录")
    @PreAuthorize("@ss.hasPermi('user:integral:remove')")
    @Log(title = "用户积分记录" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iIntegralService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
