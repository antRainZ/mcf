package org.antrain.article.controller;

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
import org.antrain.article.vo.ArticleCommentVo;
import org.antrain.article.bo.ArticleCommentQueryBo;
import org.antrain.article.bo.ArticleCommentAddBo;
import org.antrain.article.bo.ArticleCommentEditBo;
import org.antrain.article.service.IArticleCommentService;
import org.antrain.common.utils.poi.ExcelUtil;
import org.antrain.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 文章评论Controller
 * 
 * @author antrain
 * @date 2021-04-11
 */
@Api(value = "文章评论控制器", tags = {"文章评论管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/article/comment")
public class ArticleCommentController extends BaseController {

    private final IArticleCommentService iArticleCommentService;

    /**
     * 查询文章评论列表
     */
    @ApiOperation("查询文章评论列表")
    // @PreAuthorize("@ss.hasPermi('article:comment:list')")
    @GetMapping("/list")
    public TableDataInfo<ArticleCommentVo> list(ArticleCommentQueryBo bo) {
        startPage();
        List<ArticleCommentVo> list = iArticleCommentService.queryList(bo);
        return getDataTable(list);
    }

    /**
     * 导出文章评论列表
     */
    @ApiOperation("导出文章评论列表")
    //@PreAuthorize("@ss.hasPermi('article:comment:export')")
    @Log(title = "文章评论", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<ArticleCommentVo> export(ArticleCommentQueryBo bo) {
        List<ArticleCommentVo> list = iArticleCommentService.queryList(bo);
        ExcelUtil<ArticleCommentVo> util = new ExcelUtil<ArticleCommentVo>(ArticleCommentVo.class);
        return util.exportExcel(list, "comment" );
    }

    /**
     * 获取文章评论详细信息
     */
    @ApiOperation("获取文章评论详细信息")
    //@PreAuthorize("@ss.hasPermi('article:comment:query')")
    @GetMapping("/{id}")
    public AjaxResult<ArticleCommentVo> getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iArticleCommentService.queryById(id));
    }

    /**
     * 新增文章评论
     */
    @ApiOperation("新增文章评论")
    //@PreAuthorize("@ss.hasPermi('article:comment:add')")
    @Log(title = "文章评论", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult<Void> add(@RequestBody ArticleCommentAddBo bo) {
        return toAjax(iArticleCommentService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改文章评论
     */
    @ApiOperation("修改文章评论")
    //@PreAuthorize("@ss.hasPermi('article:comment:edit')")
    @Log(title = "文章评论", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult<Void> edit(@RequestBody ArticleCommentEditBo bo) {
        return toAjax(iArticleCommentService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除文章评论
     */
    @ApiOperation("删除文章评论")
    //@PreAuthorize("@ss.hasPermi('article:comment:remove')")
    @Log(title = "文章评论" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iArticleCommentService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
