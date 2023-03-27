package org.antrain.community.controller;

import java.util.List;
import java.util.Arrays;

import org.antrain.common.core.domain.model.LoginUser;
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
import org.antrain.community.vo.TopicVo;
import org.antrain.community.bo.TopicQueryBo;
import org.antrain.community.bo.TopicAddBo;
import org.antrain.community.bo.TopicEditBo;
import org.antrain.community.service.ITopicService;
import org.antrain.common.utils.poi.ExcelUtil;
import org.antrain.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 主题Controller
 * 
 * @author antrain
 * @date 2021-04-11
 */
@Api(value = "主题控制器", tags = {"主题管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/community/topic")
public class TopicController extends BaseController {

    private final ITopicService iTopicService;

    /**
     * 查询主题列表
     */
    @ApiOperation("查询主题列表")
    @PreAuthorize("@ss.hasPermi('community:topic:list')")
    @GetMapping("/list")
    public TableDataInfo<TopicVo> list(TopicQueryBo bo) {
        startPage();
        List<TopicVo> list = iTopicService.queryList(bo);
        return getDataTable(list);
    }

    /**
     * 导出主题列表
     */
    @ApiOperation("导出主题列表")
    @PreAuthorize("@ss.hasPermi('community:topic:export')")
    @Log(title = "主题", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TopicVo> export(TopicQueryBo bo) {
        List<TopicVo> list = iTopicService.queryList(bo);
        ExcelUtil<TopicVo> util = new ExcelUtil<TopicVo>(TopicVo.class);
        return util.exportExcel(list, "topic" );
    }

    /**
     * 获取主题详细信息
     */
    @ApiOperation("获取主题详细信息")
    @PreAuthorize("@ss.hasPermi('community:topic:query')")
    @GetMapping("/{id}")
    public AjaxResult<TopicVo> getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iTopicService.queryById(id));
    }

    /**
     * 新增主题
     */
    @ApiOperation("新增主题")
    @PreAuthorize("@ss.hasPermi('community:topic:add')")
    @Log(title = "主题", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult<Void> add(@RequestBody TopicAddBo bo) {
        //System.out.println(bo);
        LoginUser user = SecurityUtils.getLoginUser();
        System.out.println(user.getUser());
        bo.setUserId(1L);
        bo.setNickname("antrain");
        //return AjaxResult.success("1");
        return toAjax(iTopicService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改主题
     */
    @ApiOperation("修改主题")
    @PreAuthorize("@ss.hasPermi('community:topic:edit')")
    @Log(title = "主题", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult<Void> edit(@RequestBody TopicEditBo bo) {
        return toAjax(iTopicService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除主题
     */
    @ApiOperation("删除主题")
    @PreAuthorize("@ss.hasPermi('community:topic:remove')")
    @Log(title = "主题" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iTopicService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
