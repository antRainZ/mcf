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
import org.antrain.community.vo.InvitationVo;
import org.antrain.community.bo.InvitationQueryBo;
import org.antrain.community.bo.InvitationAddBo;
import org.antrain.community.bo.InvitationEditBo;
import org.antrain.community.service.IInvitationService;
import org.antrain.common.utils.poi.ExcelUtil;
import org.antrain.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 帖子Controller
 * 
 * @author antrain
 * @date 2021-04-11
 */
@Api(value = "帖子控制器", tags = {"帖子管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/community/invitation")
public class InvitationController extends BaseController {

    private final IInvitationService iInvitationService;

    /**
     * 查询帖子列表
     */
    @ApiOperation("查询帖子列表")
    @PreAuthorize("@ss.hasPermi('community:invitation:list')")
    @GetMapping("/list")
    public TableDataInfo<InvitationVo> list(InvitationQueryBo bo) {
        startPage();
        List<InvitationVo> list = iInvitationService.queryList(bo);
        return getDataTable(list);
    }

    /**
     * 导出帖子列表
     */
    @ApiOperation("导出帖子列表")
    @PreAuthorize("@ss.hasPermi('community:invitation:export')")
    @Log(title = "帖子", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<InvitationVo> export(InvitationQueryBo bo) {
        List<InvitationVo> list = iInvitationService.queryList(bo);
        ExcelUtil<InvitationVo> util = new ExcelUtil<InvitationVo>(InvitationVo.class);
        return util.exportExcel(list, "invitation" );
    }

    /**
     * 获取帖子详细信息
     */
    @ApiOperation("获取帖子详细信息")
    @PreAuthorize("@ss.hasPermi('community:invitation:query')")
    @GetMapping("/{id}")
    public AjaxResult<InvitationVo> getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iInvitationService.queryById(id));
    }

    /**
     * 新增帖子
     */
    @ApiOperation("新增帖子")
    @PreAuthorize("@ss.hasPermi('community:invitation:add')")
    @Log(title = "帖子", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult<Void> add(@RequestBody InvitationAddBo bo) {
//        LoginUser user = SecurityUtils.getLoginUser();
//        System.out.println(user.getUser());
        bo.setUserId(1L);
        bo.setNickname("antrain");
        return toAjax(iInvitationService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改帖子
     */
    @ApiOperation("修改帖子")
    @PreAuthorize("@ss.hasPermi('community:invitation:edit')")
    @Log(title = "帖子", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult<Void> edit(@RequestBody InvitationEditBo bo) {
        return toAjax(iInvitationService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除帖子
     */
    @ApiOperation("删除帖子")
    @PreAuthorize("@ss.hasPermi('community:invitation:remove')")
    @Log(title = "帖子" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iInvitationService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
