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
import org.antrain.user.vo.MessageVo;
import org.antrain.user.bo.MessageQueryBo;
import org.antrain.user.bo.MessageAddBo;
import org.antrain.user.bo.MessageEditBo;
import org.antrain.user.service.IMessageService;
import org.antrain.common.utils.poi.ExcelUtil;
import org.antrain.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 消息Controller
 * 
 * @author antrain
 * @date 2021-04-11
 */
@Api(value = "消息控制器", tags = {"消息管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/user/message")
public class MessageController extends BaseController {

    private final IMessageService iMessageService;

    /**
     * 查询消息列表
     */
    @ApiOperation("查询消息列表")
    @PreAuthorize("@ss.hasPermi('user:message:list')")
    @GetMapping("/list")
    public TableDataInfo<MessageVo> list(MessageQueryBo bo) {
        startPage();
        List<MessageVo> list = iMessageService.queryList(bo);
        return getDataTable(list);
    }

    /**
     * 导出消息列表
     */
    @ApiOperation("导出消息列表")
    @PreAuthorize("@ss.hasPermi('user:message:export')")
    @Log(title = "消息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<MessageVo> export(MessageQueryBo bo) {
        List<MessageVo> list = iMessageService.queryList(bo);
        ExcelUtil<MessageVo> util = new ExcelUtil<MessageVo>(MessageVo.class);
        return util.exportExcel(list, "message" );
    }

    /**
     * 获取消息详细信息
     */
    @ApiOperation("获取消息详细信息")
    @PreAuthorize("@ss.hasPermi('user:message:query')")
    @GetMapping("/{id}")
    public AjaxResult<MessageVo> getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iMessageService.queryById(id));
    }

    /**
     * 新增消息
     */
    @ApiOperation("新增消息")
    @PreAuthorize("@ss.hasPermi('user:message:add')")
    @Log(title = "消息", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult<Void> add(@RequestBody MessageAddBo bo) {
        return toAjax(iMessageService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改消息
     */
    @ApiOperation("修改消息")
    @PreAuthorize("@ss.hasPermi('user:message:edit')")
    @Log(title = "消息", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult<Void> edit(@RequestBody MessageEditBo bo) {
        return toAjax(iMessageService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除消息
     */
    @ApiOperation("删除消息")
    @PreAuthorize("@ss.hasPermi('user:message:remove')")
    @Log(title = "消息" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iMessageService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
