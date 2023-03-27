package org.antrain.community.controller;

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
import org.antrain.community.vo.InvitationCommentVo;
import org.antrain.community.bo.InvitationCommentQueryBo;
import org.antrain.community.bo.InvitationCommentAddBo;
import org.antrain.community.bo.InvitationCommentEditBo;
import org.antrain.community.service.IInvitationCommentService;
import org.antrain.common.utils.poi.ExcelUtil;
import org.antrain.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 帖子评论Controller
 * 
 * @author antrain
 * @date 2021-04-11
 */
@Api(value = "帖子评论控制器", tags = {"帖子评论管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/community/comment")
public class InvitationCommentController extends BaseController {

    private final IInvitationCommentService iInvitationCommentService;

    /**
     * 查询帖子评论列表
     */
    @ApiOperation("查询帖子评论列表")
    //@PreAuthorize("@ss.hasPermi('community:comment:list')")
    @GetMapping("/list")
    public TableDataInfo<InvitationCommentVo> list(InvitationCommentQueryBo bo) {
        startPage();
        List<InvitationCommentVo> list = iInvitationCommentService.queryList(bo);
        return getDataTable(list);
    }

    /**
     * 导出帖子评论列表
     */
    @ApiOperation("导出帖子评论列表")
    //@PreAuthorize("@ss.hasPermi('community:comment:export')")
    @Log(title = "帖子评论", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<InvitationCommentVo> export(InvitationCommentQueryBo bo) {
        List<InvitationCommentVo> list = iInvitationCommentService.queryList(bo);
        ExcelUtil<InvitationCommentVo> util = new ExcelUtil<InvitationCommentVo>(InvitationCommentVo.class);
        return util.exportExcel(list, "comment" );
    }

    /**
     * 获取帖子评论详细信息
     */
    @ApiOperation("获取帖子评论详细信息")
    //@PreAuthorize("@ss.hasPermi('community:comment:query')")
    @GetMapping("/{id}")
    public AjaxResult<InvitationCommentVo> getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iInvitationCommentService.queryById(id));
    }

    /**
     * 新增帖子评论
     */
    @ApiOperation("新增帖子评论")
    //@PreAuthorize("@ss.hasPermi('community:comment:add')")
    @Log(title = "帖子评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult<Void> add(@RequestBody InvitationCommentAddBo bo) {
        return toAjax(iInvitationCommentService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改帖子评论
     */
    @ApiOperation("修改帖子评论")
    //@PreAuthorize("@ss.hasPermi('community:comment:edit')")
    @Log(title = "帖子评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult<Void> edit(@RequestBody InvitationCommentEditBo bo) {
        return toAjax(iInvitationCommentService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除帖子评论
     */
    @ApiOperation("删除帖子评论")
    //@PreAuthorize("@ss.hasPermi('community:comment:remove')")
    @Log(title = "帖子评论" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iInvitationCommentService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
