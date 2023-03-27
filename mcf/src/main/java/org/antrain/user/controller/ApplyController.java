package org.antrain.user.controller;

import java.util.List;
import java.util.Arrays;

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
import org.antrain.user.vo.ApplyVo;
import org.antrain.user.bo.ApplyQueryBo;
import org.antrain.user.bo.ApplyAddBo;
import org.antrain.user.bo.ApplyEditBo;
import org.antrain.user.service.IApplyService;
import org.antrain.common.utils.poi.ExcelUtil;
import org.antrain.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 好友申请Controller
 * 
 * @author antrain
 * @date 2021-04-11
 */
@Api(value = "好友申请控制器", tags = {"好友申请管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/user/apply")
public class ApplyController extends BaseController {

    private final IApplyService iApplyService;

    /**
     * 查询好友申请列表
     */
    @ApiOperation("查询好友申请列表")
    @PreAuthorize("@ss.hasPermi('user:apply:list')")
    @GetMapping("/list")
    public TableDataInfo<ApplyVo> list(ApplyQueryBo bo) {
        startPage();
        List<ApplyVo> list = iApplyService.queryList(bo);
        return getDataTable(list);
    }

    /**
     * 导出好友申请列表
     */
    @ApiOperation("导出好友申请列表")
    @PreAuthorize("@ss.hasPermi('user:apply:export')")
    @Log(title = "好友申请", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<ApplyVo> export(ApplyQueryBo bo) {
        List<ApplyVo> list = iApplyService.queryList(bo);
        ExcelUtil<ApplyVo> util = new ExcelUtil<ApplyVo>(ApplyVo.class);
        return util.exportExcel(list, "apply" );
    }

    /**
     * 获取好友申请详细信息
     */
    @ApiOperation("获取好友申请详细信息")
    @PreAuthorize("@ss.hasPermi('user:apply:query')")
    @GetMapping("/{id}")
    public AjaxResult<ApplyVo> getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iApplyService.queryById(id));
    }

    /**
     * 新增好友申请
     */
    @ApiOperation("新增好友申请")
    @PreAuthorize("@ss.hasPermi('user:apply:add')")
    @Log(title = "好友申请", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult<Void> add(@RequestBody ApplyAddBo bo) {
        return toAjax(iApplyService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改好友申请
     */
    @ApiOperation("修改好友申请")
    @PreAuthorize("@ss.hasPermi('user:apply:edit')")
    @Log(title = "好友申请", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult<Void> edit(@RequestBody ApplyEditBo bo) {
        return toAjax(iApplyService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除好友申请
     */
    @ApiOperation("删除好友申请")
    @PreAuthorize("@ss.hasPermi('user:apply:remove')")
    @Log(title = "好友申请" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iApplyService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
