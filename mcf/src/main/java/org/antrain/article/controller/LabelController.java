package org.antrain.article.controller;

import java.util.List;
import java.util.Arrays;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.antrain.article.domain.Lable;
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
import org.antrain.article.vo.LableVo;
import org.antrain.article.bo.LableQueryBo;
import org.antrain.article.bo.LableAddBo;
import org.antrain.article.bo.LableEditBo;
import org.antrain.article.service.ILableService;
import org.antrain.common.utils.poi.ExcelUtil;
import org.antrain.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 标签Controller
 * 
 * @author antrain
 * @date 2021-04-11
 */
@Api(value = "标签控制器", tags = {"标签管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/article/label")
public class LabelController extends BaseController {

    private final ILableService iLableService;

    /**
     * 查询标签列表
     */
    @ApiOperation("查询标签列表")
    @PreAuthorize("@ss.hasPermi('article:lable:list')")
    @GetMapping("/list")
    public TableDataInfo<LableVo> list(LableQueryBo bo) {
        startPage();
        List<LableVo> list = iLableService.queryList(bo);
        return getDataTable(list);
    }

    /**
     * 获取所有有效的标签
     */
    @GetMapping("/all")
    public AjaxResult getAll(){
        return AjaxResult.success(iLableService.getAll());
    }

    /**
     * 导出标签列表
     */
    @ApiOperation("导出标签列表")
    @PreAuthorize("@ss.hasPermi('article:lable:export')")
    @Log(title = "标签", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<LableVo> export(LableQueryBo bo) {
        List<LableVo> list = iLableService.queryList(bo);
        ExcelUtil<LableVo> util = new ExcelUtil<LableVo>(LableVo.class);
        return util.exportExcel(list, "label" );
    }

    /**
     * 获取标签详细信息
     */
    @ApiOperation("获取标签详细信息")
    @PreAuthorize("@ss.hasPermi('article:lable:query')")
    @GetMapping("/{id}")
    public AjaxResult<LableVo> getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iLableService.queryById(id));
    }

    /**
     * 新增标签
     */
    @ApiOperation("新增标签")
    @PreAuthorize("@ss.hasPermi('article:lable:add')")
    @Log(title = "标签", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult<Void> add(@RequestBody LableAddBo bo) {
        bo.setUserId(1L);
        bo.setNickname("antrain");
        return toAjax(iLableService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改标签
     */
    @ApiOperation("修改标签")
    @PreAuthorize("@ss.hasPermi('article:lable:edit')")
    @Log(title = "标签", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult<Void> edit(@RequestBody LableEditBo bo) {
        return toAjax(iLableService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除标签
     */
    @ApiOperation("删除标签")
    @PreAuthorize("@ss.hasPermi('article:lable:remove')")
    @Log(title = "标签" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iLableService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
